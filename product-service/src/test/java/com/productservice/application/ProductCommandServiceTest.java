package com.productservice.application;

import com.productservice.application.internal.commandservice.ProductCommandService;
import com.productservice.document.model.aggregates.Product;
import com.productservice.document.model.command.CreateProductCommand;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(ProductCommandService.class)
public class ProductCommandServiceTest {

    @Autowired
    private ProductCommandService productCommandService;

    @Test
    public void testCreateProduct(){

        CreateProductCommand createProductCommand=new CreateProductCommand();
        createProductCommand.setColor("green");
        createProductCommand.setHeight(1);
        createProductCommand.setWeight(1);
        createProductCommand.setWidth(11);
        createProductCommand.setPrice(1.0);
        createProductCommand.setManufactureDate(LocalDate.now());
        createProductCommand.setName("test");
        createProductCommand.setProductTypeCode("CR");

        Product product=productCommandService.saveProduct(createProductCommand);

        Assertions.assertThat(product.getId()).isNotNull();
        Assertions.assertThat(product.getProductDetail().getId()).isNotNull();
        Assertions.assertThat(product.getProductCategory().getId()).isNotNull();

    }


}
