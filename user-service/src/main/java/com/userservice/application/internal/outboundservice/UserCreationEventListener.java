package com.userservice.application.internal.outboundservice;

import com.userservice.config.UserTopicsConfigProperties;
import com.userservice.shareddomain.event.UserCreatedEvent;
import com.userservice.shareddomain.event.UserCreatedEventData;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class UserCreationEventListener {

    private KafkaTemplate<String, UserCreatedEventData> kafkaTemplate;
    private UserTopicsConfigProperties userTopicsConfigProperties;

    public UserCreationEventListener(KafkaTemplate kafkaTemplate,UserTopicsConfigProperties userTopicsConfigProperties){
        this.kafkaTemplate=kafkaTemplate;
        this.userTopicsConfigProperties=userTopicsConfigProperties;
    }

    @TransactionalEventListener
    public void publishUserCreatedEvent(UserCreatedEvent userCreatedEvent){
        kafkaTemplate.send(userTopicsConfigProperties.getUserCreationTopic(),(UserCreatedEventData)userCreatedEvent.getSource());
    }

}
