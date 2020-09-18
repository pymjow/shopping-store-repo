package com.warehouseservice.infrastructure.repositories;

import com.warehouseservice.document.model.entity.WarehouseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseTypeRepository extends JpaRepository<WarehouseType, Long> {
}
