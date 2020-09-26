package com.productservice.interfaces.rest.transformer;

import com.productservice.document.model.command.CreateProductCommand;
import com.productservice.interfaces.rest.dto.CreateProductDto;

public class CreateProductDtoToCommandAssembler {

    public static CreateProductCommand toCreateProductCommand(CreateProductDto createProductDto){

        CreateProductCommand createProductCommand=new CreateProductCommand();
        createProductCommand.setProductTypeCode(createProductDto.getProductTypeCode());
        createProductCommand.setName(createProductDto.getName());
        createProductCommand.setManufactureDate(createProductDto.getManufactureDate());
        createProductCommand.setPrice(createProductDto.getPrice());
        createProductCommand.setWidth(createProductDto.getWidth());
        createProductCommand.setWeight(createProductDto.getWeight());
        createProductCommand.setHeight(createProductDto.getHeight());
        createProductCommand.setColor(createProductDto.getColor());
        return createProductCommand;
    }

}
