package com.userservice.document.model.aggregates;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Product {

    @Column
    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
