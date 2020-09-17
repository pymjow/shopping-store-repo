package com.productservice.infrastructure;

import com.productservice.infrastructure.repositories.ProductCategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void testFetchAll(){
        Assertions.assertThat(productCategoryRepository.findAll().isEmpty()).isEqualTo(false);
    }

}
