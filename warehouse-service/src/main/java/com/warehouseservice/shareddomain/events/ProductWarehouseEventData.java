package com.warehouseservice.shareddomain.events;

public class ProductWarehouseEventData {

    private Long warehouseId;
    private Long productId;
    private Integer productCount;

    public ProductWarehouseEventData(Long warehouseId, Long productId, Integer productCount) {
        this.warehouseId = warehouseId;
        this.productId = productId;
        this.productCount = productCount;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
