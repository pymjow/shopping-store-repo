package com.productservice.application;

import com.productservice.application.internal.queryservice.ProductQueryService;
import com.productservice.document.model.aggregates.Product;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(ProductQueryService.class)
public class ProductQueryServiceTest {

    @Autowired
    private ProductQueryService productQueryService;

    @Test
    public void testFetchWithDetail(){
        List<Product> productList=productQueryService.getProductList();
        Assertions.assertThat(productList).isNotEmpty();
        productList.forEach(q->Assertions.assertThat(q.getProductDetail().getId()).isNotNull());
    }


}
