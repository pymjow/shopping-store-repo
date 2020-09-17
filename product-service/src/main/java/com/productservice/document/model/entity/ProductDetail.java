package com.productservice.document.model.entity;

import com.productservice.document.model.valueobjects.DetailSpecification;

import javax.persistence.*;

@Entity
public class ProductDetail {

    private Long id;
    private DetailSpecification detailSpecification;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Embedded
    public DetailSpecification getDetailSpecification() {
        return detailSpecification;
    }

    public void setDetailSpecification(DetailSpecification detailSpecification) {
        this.detailSpecification = detailSpecification;
    }
}
