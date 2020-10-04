package com.warehouseservice.interfaces.kafka;

import org.springframework.messaging.SubscribableChannel;

public interface ProductEventSource {

    String PRODUCT_CREATION="productCreationSubscriber";

    SubscribableChannel productCreationSubscriber();


}
