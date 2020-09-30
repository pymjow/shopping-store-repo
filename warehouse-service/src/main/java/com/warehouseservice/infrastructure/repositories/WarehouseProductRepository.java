package com.warehouseservice.infrastructure.repositories;

import com.warehouseservice.document.model.entity.WarehouseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WarehouseProductRepository extends JpaRepository<WarehouseProduct, Long> {

    @Query("select p from WarehouseProduct  p where p.product.productId=:productId")
    Optional<WarehouseProduct> findByProductId(Long productId);

}
