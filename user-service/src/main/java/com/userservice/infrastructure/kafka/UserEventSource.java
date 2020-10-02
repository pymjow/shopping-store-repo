package com.userservice.infrastructure.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserEventSource {

    @Output
    MessageChannel userCreationChannel();


}
