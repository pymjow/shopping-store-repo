package com.productservice.infrastructure.repositories;

import com.productservice.document.model.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCommentRepository extends JpaRepository<ProductComment,Long> {

}
