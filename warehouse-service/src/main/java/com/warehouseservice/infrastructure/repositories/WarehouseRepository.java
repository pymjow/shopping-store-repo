package com.warehouseservice.infrastructure.repositories;

import com.warehouseservice.document.model.aggregates.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}
