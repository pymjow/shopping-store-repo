package com.warehouseservice.document.model.aggregates;

import com.warehouseservice.document.model.entity.WarehouseType;
import com.warehouseservice.document.model.valueobjects.WareHouseSpecification;

import javax.persistence.*;

@Entity
public class Warehouse {

    private Long id;
    private WareHouseSpecification wareHouseSpecification;
    private WarehouseType warehouseType;

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

    @ManyToOne
    @JoinColumn(name = "warehouse_type_id")
    public WarehouseType getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(WarehouseType warehouseType) {
        this.warehouseType = warehouseType;
    }
}
