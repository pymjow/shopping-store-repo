package com.userservice.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userservice.application.internal.outboundservice.UserCreationEventListener;
import com.userservice.config.KafkaProducerConfig;
import com.userservice.config.UserTopicsConfigProperties;
import com.userservice.shareddomain.event.UserCreatedEvent;
import com.userservice.shareddomain.event.UserCreatedEventData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@DirtiesContext
@SpringBootTest(classes = {UserCreationEventListenerTest.KafkaTemplateConfig.class,UserCreationEventListener.class, UserTopicsConfigProperties.class})
public class UserCreationEventListenerTest {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public static final String TOPIC_NAME="user_creation_topic";

    private BlockingQueue<ConsumerRecord<String,String>> consumerRecords;

    private KafkaMessageListenerContainer<String,UserCreatedEventData> container;

    @ClassRule
    public static EmbeddedKafkaRule embeddedKafkaRule=
            new EmbeddedKafkaRule(1,true,TOPIC_NAME);

    @Before
    public void setup(){
        consumerRecords=new LinkedBlockingQueue<>();

        ContainerProperties containerProperties=new ContainerProperties(TOPIC_NAME);
        Map<String,Object> consumerProperties= KafkaTestUtils.consumerProps("sender","false",embeddedKafkaRule.getEmbeddedKafka());
        DefaultKafkaConsumerFactory<String, UserCreatedEventData> consumer=new DefaultKafkaConsumerFactory<>(consumerProperties);
        container=new KafkaMessageListenerContainer<>(consumer,containerProperties);
        container.setupMessageListener((MessageListener<String,String>) record->{
            consumerRecords.add(record);
        });

        container.start();

        ContainerTestUtils.waitForAssignment(container, embeddedKafkaRule.getEmbeddedKafka().getPartitionsPerTopic());

    }

    @After
    public void tearDown(){
        container.stop();
    }

    @Test
    public void testUserCreation() throws InterruptedException, JsonProcessingException {

        UserCreatedEventData userCreatedEventData=new UserCreatedEventData(1L);
        UserCreatedEvent userCreatedEvent=new UserCreatedEvent(userCreatedEventData);
        applicationEventPublisher.publishEvent(userCreatedEvent);

        ConsumerRecord<String,String> received=consumerRecords.poll(10, TimeUnit.SECONDS);
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(userCreatedEventData);

        Assertions.assertThat(received.value()).isEqualTo(json);

    }

    @Configuration
    public static class KafkaTemplateConfig{

        @Bean
        public KafkaTemplate<String,UserCreatedEventData> kafkaTemplate(){

            Map<String, Object> senderProps = KafkaTestUtils.producerProps(embeddedKafkaRule.getEmbeddedKafka());
            senderProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            ProducerFactory<String, UserCreatedEventData> pf = new DefaultKafkaProducerFactory<>(senderProps);
            KafkaTemplate<String , UserCreatedEventData> template = new KafkaTemplate<>(pf);
            return template;
        }

    }

}
