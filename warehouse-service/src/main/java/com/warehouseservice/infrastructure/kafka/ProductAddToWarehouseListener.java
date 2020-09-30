package com.warehouseservice.infrastructure.kafka;

import com.warehouseservice.shareddomain.events.ProductAdd2WarehouseEvent;
import com.warehouseservice.shareddomain.events.ProductWarehouseEventData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ProductAddToWarehouseListener {

    private KafkaTemplate<String, ProductWarehouseEventData> kafkaTemplate;

    @Value("${com.kafka.add-product-topic}")
    private String addProductTopic;

    public  ProductAddToWarehouseListener(KafkaTemplate<String, ProductWarehouseEventData> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    @TransactionalEventListener
    public void listen(ProductAdd2WarehouseEvent event){
        ProductWarehouseEventData productWarehouseEventData=(ProductWarehouseEventData)event.getSource();
        kafkaTemplate.send(addProductTopic,productWarehouseEventData);
    }

}
