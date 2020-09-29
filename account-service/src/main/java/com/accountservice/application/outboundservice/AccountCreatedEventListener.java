package com.accountservice.application.outboundservice;

import com.accountservice.shareddomain.event.AccountCreatedEvent;
import com.accountservice.shareddomain.event.AccountCreatedEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class AccountCreatedEventListener {

    @Value("${com.kafka.accountCreationTopic:account_creation_topic}")
    private String createAccountTopic;

    @Autowired
    private KafkaTemplate<String,AccountCreatedEventData> kafkaTemplate;

    @EventListener
    public void listen(AccountCreatedEvent accountCreatedEvent){

        AccountCreatedEventData accountCreatedEventData=(AccountCreatedEventData)accountCreatedEvent.getSource();
        kafkaTemplate.send(createAccountTopic,accountCreatedEventData);

    }

}
