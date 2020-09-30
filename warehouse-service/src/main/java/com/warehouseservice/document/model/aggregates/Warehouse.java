package com.warehouseservice.document.model.aggregates;

import com.warehouseservice.document.model.command.InsertProduct2WarehouseCommand;
import com.warehouseservice.document.model.entity.WarehouseProduct;
import com.warehouseservice.document.model.valueobjects.WareHouseSpecification;
import com.warehouseservice.document.model.valueobjects.WarehouseProductSpecification;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Entity
public class Warehouse extends AbstractAggregateRoot<Warehouse> {

    private Long id;
    private WareHouseSpecification wareHouseSpecification;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Embedded
    public WareHouseSpecification getWareHouseSpecification() {
        return wareHouseSpecification;
    }

    public void setWareHouseSpecification(WareHouseSpecification wareHouseSpecification) {
        this.wareHouseSpecification = wareHouseSpecification;
    }

    public WarehouseProduct addProduct2Warehouse(InsertProduct2WarehouseCommand command){
        WarehouseProduct warehouseProduct=new WarehouseProduct();
        warehouseProduct.setWarehouse(this);
        warehouseProduct.setWarehouseProductSpecification(new WarehouseProductSpecification());
        return warehouseProduct;
    }

    public WarehouseProduct addProduct2Warehouse(WarehouseProduct warehouseProduct){
        warehouseProduct.getWarehouseProductSpecification().increment();
        return warehouseProduct;
    }

}
