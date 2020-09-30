package com.productservice.application.internal.commandservice;

import com.productservice.document.model.aggregates.Product;
import com.productservice.document.model.command.CreateProductCommand;
import com.productservice.document.model.entity.ProductCategory;
import com.productservice.document.model.entity.ProductDetail;
import com.productservice.infrastructure.repositories.ProductCategoryRepository;
import com.productservice.infrastructure.repositories.ProductDetailRepository;
import com.productservice.infrastructure.repositories.ProductRepository;
import com.productservice.sharedmodel.event.ProductCreatedEvent;
import com.productservice.sharedmodel.event.ProductCreatedEventData;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductCommandService {

    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;
    private ProductDetailRepository productDetailRepository;
    private ApplicationEventPublisher applicationEventPublisher;

    public ProductCommandService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository,ProductDetailRepository productDetailRepository,ApplicationEventPublisher applicationEventPublisher){
        this.productRepository=productRepository;
        this.productCategoryRepository=productCategoryRepository;
        this.productDetailRepository=productDetailRepository;
        this.applicationEventPublisher=applicationEventPublisher;
    }

    @Transactional(rollbackOn = Exception.class)
    public Product saveProduct(CreateProductCommand createProductCommand){

        Product product=new Product(createProductCommand);
        ProductDetail productDetail=product.getProductDetail();
        ProductCategory productCategory=productCategoryRepository.findByCode(createProductCommand.getProductTypeCode());
        product.setProductCategory(productCategory);
        product=productRepository.save(product);
        productDetail.setId(product.getId());
        productDetail=productDetailRepository.save(productDetail);
        product.setProductDetail(productDetail);
        applicationEventPublisher.publishEvent(new ProductCreatedEvent(new ProductCreatedEventData(product.getId())));
        return product;
    }

}
