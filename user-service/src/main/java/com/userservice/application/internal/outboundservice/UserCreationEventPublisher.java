package com.userservice.application.internal.outboundservice;

import com.userservice.infrastructure.kafka.UserEventSource;
import com.userservice.shareddomain.event.UserCreatedEvent;
import com.userservice.shareddomain.event.UserCreatedEventData;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@EnableBinding(UserEventSource.class)
public class UserCreationEventPublisher {

    private UserEventSource userEventSource;

    public UserCreationEventPublisher(UserEventSource userEventSource){
        this.userEventSource=userEventSource;
    }

    @TransactionalEventListener
    public void publishUserCreatedEvent(UserCreatedEvent userCreatedEvent){
        UserCreatedEventData userCreatedEventData=(UserCreatedEventData)userCreatedEvent.getSource();
        userEventSource.userCreationChannel().send(MessageBuilder.withPayload(userCreatedEventData).build());
    }

}
