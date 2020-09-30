package com.warehouseservice.interfaces.kafka.transformer;

import com.warehouseservice.document.model.command.InsertProduct2WarehouseCommand;
import com.warehouseservice.shareddomain.message.ProductCreatedMessage;

public class ProductMessageToAddWarehouseAssembler {

    private ProductMessageToAddWarehouseAssembler(){

    }

    public static InsertProduct2WarehouseCommand toInsertProduct2WarehouseCommand(ProductCreatedMessage message){
        InsertProduct2WarehouseCommand command=new InsertProduct2WarehouseCommand();
        command.setProductId(message.getProductId());
        return command;
    }


}
