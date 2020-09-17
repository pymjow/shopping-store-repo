package com.productservice.infrastructure;

import com.productservice.document.model.aggregates.Product;
import com.productservice.document.model.entity.ProductCategory;
import com.productservice.document.model.valueobjects.ProductSpecification;
import com.productservice.infrastructure.repositories.ProductCategoryRepository;
import com.productservice.infrastructure.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void fetchAllTest() {
        Assertions.assertThat(productRepository.findAll().isEmpty()).isEqualTo(false);
    }

    @Test
    public void testSave(){

        ProductCategory productCategory=productCategoryRepository.getOne(1L);

        ProductSpecification productSpecification=new ProductSpecification();
        productSpecification.setManufactureDate(LocalDate.now());
        productSpecification.setName("test");
        productSpecification.setPrice(2000.4);

        Product product=new Product();
        product.setProductCategory(productCategory);
        product.setProductSpecification(productSpecification);

        product=productRepository.save(product);

        Assertions.assertThat(product.getId()).isNotNull();

    }
}
