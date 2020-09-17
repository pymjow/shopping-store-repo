package com.productservice.infrastructure.repositories;

import com.productservice.document.model.aggregates.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
