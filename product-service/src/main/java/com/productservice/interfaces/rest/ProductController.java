package com.productservice.interfaces.rest;

import com.productservice.application.internal.queryservice.ProductQueryService;
import com.productservice.application.internal.queryservice.commandservice.ProductCommandService;
import com.productservice.document.model.aggregates.Product;
import com.productservice.interfaces.rest.dto.CreateProductDto;
import com.productservice.interfaces.rest.dto.ProductInquiryDto;
import com.productservice.interfaces.rest.transformer.CreateProductDtoToCommandAssembler;
import com.productservice.interfaces.rest.transformer.ProductToInquriryDtoAssembler;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductQueryService productQueryService;
    private ProductCommandService productCommandService;

    public ProductController(ProductQueryService productQueryService, ProductCommandService productCommandService) {
        this.productQueryService = productQueryService;
        this.productCommandService = productCommandService;
    }

    @GetMapping("/all")
    public List<ProductInquiryDto> findAllProducts() {
        return productQueryService.getProductList()
                .stream().map(ProductToInquriryDtoAssembler::productToInquiryDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public ProductInquiryDto createProduct(@RequestBody CreateProductDto createProductDto) {

        Product product = productCommandService.saveProduct(CreateProductDtoToCommandAssembler.toCreateProductCommand(createProductDto));
       return ProductToInquriryDtoAssembler.productToInquiryDto(product);

    }


}
