package com.productservice.document.model.aggregates;

import com.productservice.document.model.entity.ProductCategory;
import com.productservice.document.model.valueobjects.ProductSpecification;

import javax.persistence.*;

@Entity
public class Product {

    private Long id;
    private ProductSpecification productSpecification;
    private ProductCategory productCategory;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Embedded
    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
