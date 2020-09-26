package com.productservice.application.internal.queryservice;

import com.productservice.document.model.aggregates.Product;
import com.productservice.infrastructure.repositories.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {

    private ProductRepository productRepository;

    public ProductQueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductList(){
        return productRepository.findWithDetail();
    }

}
