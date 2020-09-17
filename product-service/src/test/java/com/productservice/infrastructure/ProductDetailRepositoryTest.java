package com.productservice.infrastructure;

import com.productservice.document.model.entity.ProductDetail;
import com.productservice.document.model.valueobjects.DetailSpecification;
import com.productservice.infrastructure.repositories.ProductDetailRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductDetailRepositoryTest {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Test
    public void testFetchAll(){
        Assertions.assertThat(productDetailRepository.findAll().isEmpty()).isEqualTo(false);
    }

    @Test
    public void saveDetail(){

        DetailSpecification detailSpecification=new DetailSpecification();
        detailSpecification.setColor("BLUE");
        detailSpecification.setHeight(2);
        detailSpecification.setWidth(1);
        detailSpecification.setWeight(2);

        ProductDetail productDetail=new ProductDetail();
        productDetail.setId(3L);
        productDetail.setDetailSpecification(detailSpecification);

        productDetail=productDetailRepository.save(productDetail);

        Assertions.assertThat(productDetail.getId()).isNotNull();

    }

}
