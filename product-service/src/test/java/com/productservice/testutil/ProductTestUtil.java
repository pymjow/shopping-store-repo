package com.productservice.testutil;

import com.productservice.document.model.aggregates.Product;
import com.productservice.document.model.entity.ProductDetail;
import com.productservice.document.model.valueobjects.DetailSpecification;
import com.productservice.document.model.valueobjects.ProductSpecification;
import org.mockito.Mockito;

import java.time.LocalDate;

public class ProductTestUtil {

    public static Product mockProductWithId(Long id){
        Product product= Mockito.mock(Product.class);
        Mockito.when(product.getId()).thenReturn(id);
        Mockito.when(product.getProductSpecification()).thenReturn(new ProductSpecification("test",1.0, LocalDate.now()));
        ProductDetail productDetail=Mockito.mock(ProductDetail.class);
        Mockito.when(productDetail.getId()).thenReturn(1L);
        DetailSpecification detailSpecification=Mockito.mock(DetailSpecification.class);
        Mockito.when(detailSpecification.getColor()).thenReturn("green");
        Mockito.when(productDetail.getDetailSpecification()).thenReturn(detailSpecification);
        Mockito.when(product.getProductDetail()).thenReturn(productDetail);
        return product;
    }
}
