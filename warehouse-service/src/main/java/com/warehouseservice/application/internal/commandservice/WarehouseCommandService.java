package com.warehouseservice.application.internal.commandservice;

import com.warehouseservice.document.model.aggregates.Warehouse;
import com.warehouseservice.document.model.command.InsertProduct2WarehouseCommand;
import com.warehouseservice.document.model.entity.WarehouseProduct;
import com.warehouseservice.infrastructure.repositories.WarehouseProductRepository;
import com.warehouseservice.infrastructure.repositories.WarehouseRepository;
import com.warehouseservice.shareddomain.events.ProductAdd2WarehouseEvent;
import com.warehouseservice.shareddomain.events.ProductWarehouseEventData;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseCommandService {


    private WarehouseRepository warehouseRepository;
    private WarehouseProductRepository warehouseProductRepository;
    private ApplicationEventPublisher applicationEventPublisher;


    public WarehouseCommandService(WarehouseRepository warehouseRepository, WarehouseProductRepository warehouseProductRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseProductRepository = warehouseProductRepository;
        this.applicationEventPublisher=applicationEventPublisher;
    }

    public void addProductToRepository(InsertProduct2WarehouseCommand command) {

        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(1L);
        if (warehouseOptional.isPresent()) {
            Warehouse warehouse = warehouseOptional.get();
            Optional<WarehouseProduct> warehouseProductOptional = warehouseProductRepository.findByProductId(command.getProductId());
            WarehouseProduct warehouseProduct = null;
            if (warehouseProductOptional.isPresent()) {
                warehouseProduct=warehouse.addProduct2Warehouse(warehouseProductOptional.get());
            } else {
                warehouseProduct = warehouse.addProduct2Warehouse(command);
            }
            warehouseProduct=warehouseProductRepository.save(warehouseProduct);
            applicationEventPublisher.publishEvent(new ProductAdd2WarehouseEvent(new ProductWarehouseEventData(warehouse.getId(),warehouseProduct.getProduct().getProductId(),warehouseProduct.getWarehouseProductSpecification().getCount())));
        }


    }

}
