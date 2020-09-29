package com.accountservice.interfaces.kafka;


import com.accountservice.application.commandservice.AccountCommandService;
import com.accountservice.interfaces.kafka.transformer.CreateEvent2AccountCommandAssembler;
import com.accountservice.shareddomain.event.UserCreatedEventData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserCreationConsumer {

    private AccountCommandService accountCommandService;

    public UserCreationConsumer(AccountCommandService accountCommandService){
        this.accountCommandService = accountCommandService;
    }


    @KafkaListener(topics = "${com.kafka.userCreationTopic:user_creation_topic}")
    public void listenToUserCreation(@Payload UserCreatedEventData message){
        accountCommandService.createAccount(CreateEvent2AccountCommandAssembler.toCreateAccount(message));
    }

}
