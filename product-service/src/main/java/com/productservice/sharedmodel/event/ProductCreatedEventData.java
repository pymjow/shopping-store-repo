package com.productservice.sharedmodel.event;

public class ProductCreatedEventData {

    private Long productId;

    public ProductCreatedEventData(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
