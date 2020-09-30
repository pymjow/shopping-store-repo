package com.productservice.sharedmodel.event;

import org.springframework.context.ApplicationEvent;

public class ProductCreatedEvent extends ApplicationEvent {

    public ProductCreatedEvent(Object source) {
        super(source);
    }
}
