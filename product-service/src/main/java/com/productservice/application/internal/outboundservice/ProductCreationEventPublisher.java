package com.productservice.application.internal.outboundservice;

import com.productservice.infrastructure.kafka.ProductEventSource;
import com.productservice.sharedmodel.event.ProductCreatedEvent;
import com.productservice.sharedmodel.event.ProductCreatedEventData;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@EnableBinding(ProductEventSource.class)
public class ProductCreationEventPublisher {

    private ProductEventSource productEventSource;

    public ProductCreationEventPublisher(ProductEventSource productEventSource){
        this.productEventSource=productEventSource;
    }

    @TransactionalEventListener
    public void ProductCreatedEventListener(ProductCreatedEvent productCreatedEvent){
        ProductCreatedEventData productCreatedEventData=(ProductCreatedEventData)productCreatedEvent.getSource();
        productEventSource.productCreationChannel().send(MessageBuilder.withPayload(productCreatedEventData).build());
    }

}
