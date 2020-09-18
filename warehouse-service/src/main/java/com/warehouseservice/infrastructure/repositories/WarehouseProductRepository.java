package com.warehouseservice.infrastructure.repositories;

import com.warehouseservice.document.model.entity.WarehouseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseProductRepository extends JpaRepository<WarehouseProduct, Long> {
}
