package com.productservice.infrastructure.repositories;

import com.productservice.document.model.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

}
