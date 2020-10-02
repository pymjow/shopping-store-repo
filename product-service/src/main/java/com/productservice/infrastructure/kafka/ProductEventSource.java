package com.productservice.infrastructure.kafka;

import org.springframework.messaging.MessageChannel;

public interface ProductEventSource {

    MessageChannel productCreationChannel();

}
