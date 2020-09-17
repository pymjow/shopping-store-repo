package com.productservice.infrastructure;

import com.productservice.document.model.aggregates.Product;
import com.productservice.document.model.aggregates.User;
import com.productservice.document.model.entity.ProductComment;
import com.productservice.infrastructure.repositories.ProductCommentRepository;
import com.productservice.infrastructure.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductCommentRepositoryTest {

    @Autowired
    private ProductCommentRepository productCommentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFetchAll(){
        Assertions.assertThat(productCommentRepository.findAll().isEmpty()).isEqualTo(false);
    }

    @Test
    public void testSave(){

        User user=new User();
        user.setUserId(1L);

        Product product=productRepository.getOne(1L);

        ProductComment productComment=new ProductComment();
        productComment.setMessage("hello");
        productComment.setUser(user);
        productComment.setProduct(product);

        productComment=productCommentRepository.save(productComment);
        Assertions.assertThat(productComment.getId()).isNotNull();

    }


}
