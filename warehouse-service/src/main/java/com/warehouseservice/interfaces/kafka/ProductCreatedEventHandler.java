package com.warehouseservice.interfaces.kafka;

import com.warehouseservice.application.internal.commandservice.WarehouseCommandService;
import com.warehouseservice.interfaces.kafka.transformer.ProductMessageToAddWarehouseAssembler;
import com.warehouseservice.shareddomain.message.ProductCreatedMessage;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(ProductEventSource.class)
public class ProductCreatedEventHandler {

   private ProductEventSource productEventSource;
   private WarehouseCommandService warehouseCommandService;

   public ProductCreatedEventHandler(ProductEventSource productEventSource,WarehouseCommandService warehouseCommandService){
       this.productEventSource=productEventSource;
       this.warehouseCommandService=warehouseCommandService;
   }

    @StreamListener(target = ProductEventSource.PRODUCT_CREATION)
    public void listen(@Payload ProductCreatedMessage message){
        warehouseCommandService.addProductToRepository(ProductMessageToAddWarehouseAssembler.toInsertProduct2WarehouseCommand(message));
    }

}
