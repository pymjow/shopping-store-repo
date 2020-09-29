package com.accountservice.application;

import com.accountservice.application.commandservice.AccountCommandService;
import com.accountservice.application.outboundservice.AccountCreatedEventListener;
import com.accountservice.document.model.aggregates.Account;
import com.accountservice.document.model.command.CreateAccountCommand;
import com.accountservice.shareddomain.event.AccountCreatedEventData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;



@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigurationPackage(basePackages = "comm.accountservice")
@DataJpaTest
@EnableJpaRepositories(basePackages = "com.accountservice")
@EntityScan("com.accountservice")
@Import(  {AccountCommandService.class, AccountCreatedEventListener.class, CreateAccountCommandTest.KafkaTemplateConfig.class})
public class CreateAccountCommandTest {

    @Autowired
    private AccountCommandService accountCommandService;

    public static final String TOPIC_NAME="account_creation_topic";

    private BlockingQueue<ConsumerRecord<String,String>> consumerRecords;

    private KafkaMessageListenerContainer<String, AccountCreatedEventData> container;

    @ClassRule
    public static EmbeddedKafkaRule embeddedKafkaRule=
            new EmbeddedKafkaRule(1,true,TOPIC_NAME);


    @Before
    public void setup(){

        consumerRecords=new LinkedBlockingDeque<>();

        ContainerProperties containerProperties=new ContainerProperties(TOPIC_NAME);
        Map<String,Object> consumerProperties= KafkaTestUtils.consumerProps("sender","false",embeddedKafkaRule.getEmbeddedKafka());
        DefaultKafkaConsumerFactory<String, AccountCreatedEventData> consumer=new DefaultKafkaConsumerFactory<>(consumerProperties);
        container=new KafkaMessageListenerContainer<>(consumer,containerProperties);
        container.setupMessageListener((MessageListener<String,String>) record->{
            consumerRecords.add(record);
        });

        container.start();

        ContainerTestUtils.waitForAssignment(container, embeddedKafkaRule.getEmbeddedKafka().getPartitionsPerTopic());

    }


    @Test
    public void testCreateAccount() throws JsonProcessingException, InterruptedException {

        CreateAccountCommand createAccountCommand=new CreateAccountCommand();
        createAccountCommand.setUserId(1L);

        Account account=accountCommandService.createAccount(createAccountCommand);
        Assertions.assertThat(account.getId()).isNotNull();

        AccountCreatedEventData accountCreatedEventData=new AccountCreatedEventData(1L);

        ConsumerRecord<String,String> received=consumerRecords.poll(10, TimeUnit.SECONDS);
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(accountCreatedEventData);

        Assertions.assertThat(received.value()).isEqualTo(json);

    }

    @Configuration
    public static class KafkaTemplateConfig{

        @Bean
        public KafkaTemplate<String,AccountCreatedEventData> kafkaTemplate(){

            Map<String, Object> senderProps = KafkaTestUtils.producerProps(embeddedKafkaRule.getEmbeddedKafka());
            senderProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            ProducerFactory<String, AccountCreatedEventData> pf = new DefaultKafkaProducerFactory<>(senderProps);
            KafkaTemplate<String , AccountCreatedEventData> template = new KafkaTemplate<>(pf);
            return template;
        }

    }


}
