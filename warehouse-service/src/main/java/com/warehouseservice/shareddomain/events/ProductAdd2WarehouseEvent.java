package com.warehouseservice.shareddomain.events;

import org.springframework.context.ApplicationEvent;

public class ProductAdd2WarehouseEvent extends ApplicationEvent {

    public ProductAdd2WarehouseEvent(Object source) {
        super(source);
    }
}
