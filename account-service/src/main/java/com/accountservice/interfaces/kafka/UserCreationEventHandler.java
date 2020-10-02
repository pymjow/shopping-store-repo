package com.accountservice.interfaces.kafka;


import com.accountservice.application.commandservice.AccountCommandService;
import com.accountservice.interfaces.kafka.transformer.CreateEvent2AccountCommandAssembler;
import com.accountservice.shareddomain.event.UserCreatedEventData;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(UserEventSource.class)
public class UserCreationEventHandler {

    private AccountCommandService accountCommandService;

    public UserCreationEventHandler(AccountCommandService accountCommandService){
        this.accountCommandService = accountCommandService;
    }


    @StreamListener(target = UserEventSource.USER_CREATION)
    public void listenToUserCreation(@Payload UserCreatedEventData message){
        accountCommandService.createAccount(CreateEvent2AccountCommandAssembler.toCreateAccount(message));
    }

}
