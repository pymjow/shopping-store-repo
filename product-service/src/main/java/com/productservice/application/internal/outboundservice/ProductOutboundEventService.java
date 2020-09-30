package com.productservice.application.internal.outboundservice;

import com.productservice.sharedmodel.event.ProductCreatedEvent;
import com.productservice.sharedmodel.event.ProductCreatedEventData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class ProductOutboundEventService {

    private KafkaTemplate kafkaTemplate;

    @Value("${com.kafka.product-create-topic}")
    private String productCreateTopic;

    public ProductOutboundEventService(KafkaTemplate<String,ProductCreatedEventData> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    @TransactionalEventListener
    public void ProductCreatedEventListener(ProductCreatedEvent productCreatedEvent){
        ProductCreatedEventData productCreatedEventData=(ProductCreatedEventData)productCreatedEvent.getSource();
        kafkaTemplate.send(productCreateTopic,productCreatedEventData);
    }

}
