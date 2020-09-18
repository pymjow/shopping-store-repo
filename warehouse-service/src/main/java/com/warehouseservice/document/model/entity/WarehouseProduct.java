package com.warehouseservice.document.model.entity;

import com.warehouseservice.document.model.aggregates.Product;
import com.warehouseservice.document.model.aggregates.Warehouse;
import com.warehouseservice.document.model.valueobjects.WarehouseProductSpecification;

import javax.persistence.*;

@Entity
public class WarehouseProduct {

    private Long id;
    private Product product;
    private Warehouse warehouse;
    private WarehouseProductSpecification warehouseProductSpecification;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Embedded
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Embedded
    public WarehouseProductSpecification getWarehouseProductSpecification() {
        return warehouseProductSpecification;
    }

    public void setWarehouseProductSpecification(WarehouseProductSpecification warehouseProductSpecification) {
        this.warehouseProductSpecification = warehouseProductSpecification;
    }
}
