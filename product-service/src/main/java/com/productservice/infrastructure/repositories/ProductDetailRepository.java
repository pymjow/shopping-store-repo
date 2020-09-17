package com.productservice.infrastructure.repositories;

import com.productservice.document.model.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {


}
