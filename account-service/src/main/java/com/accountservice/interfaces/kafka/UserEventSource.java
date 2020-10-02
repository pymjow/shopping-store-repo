package com.accountservice.interfaces.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserEventSource {

    String USER_CREATION="userCreationSubscriber";

    @Input
    SubscribableChannel userCreationSubscriber();

}
