package com.warehouseservice.document.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WarehouseProductSpecification {

    private Integer count;

    public WarehouseProductSpecification(){
        this.count=1;
    }

    @Column
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void increment(){
        this.count++;
    }

}
