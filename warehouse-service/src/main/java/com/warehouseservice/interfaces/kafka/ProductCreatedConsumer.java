package com.warehouseservice.interfaces.kafka;

import com.warehouseservice.application.internal.commandservice.WarehouseCommandService;
import com.warehouseservice.interfaces.kafka.transformer.ProductMessageToAddWarehouseAssembler;
import com.warehouseservice.shareddomain.message.ProductCreatedMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProductCreatedConsumer {

    private WarehouseCommandService warehouseCommandService;

    public ProductCreatedConsumer(WarehouseCommandService warehouseCommandService){
        this.warehouseCommandService=warehouseCommandService;
    }

    @KafkaListener
    public void listen(@Payload ProductCreatedMessage message){
        warehouseCommandService.addProductToRepository(ProductMessageToAddWarehouseAssembler.toInsertProduct2WarehouseCommand(message));
    }

}
