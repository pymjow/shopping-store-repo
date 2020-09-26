package com.productservice.interfaces.rest.transformer;

import com.productservice.document.model.aggregates.Product;
import com.productservice.interfaces.rest.dto.ProductInquiryDto;

public class ProductToInquriryDtoAssembler {

    private ProductToInquriryDtoAssembler(){

    }

    public static ProductInquiryDto productToInquiryDto(Product product){

        ProductInquiryDto productInquiryDto=new ProductInquiryDto();
        productInquiryDto.setColor(product.getProductDetail().getDetailSpecification().getColor());
        productInquiryDto.setManufactureDate(product.getProductSpecification().getManufactureDate());
        productInquiryDto.setName(product.getProductSpecification().getName());
        productInquiryDto.setPrice(product.getProductSpecification().getPrice());
        return productInquiryDto;
    }


}
