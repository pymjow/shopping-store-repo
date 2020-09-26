package com.productservice.document.model.command;

import com.productservice.document.model.valueobjects.DetailSpecification;
import com.productservice.document.model.valueobjects.ProductSpecification;

import java.time.LocalDate;
import java.util.Objects;

public class CreateProductCommand {

    private String name;
    private Double price;
    private LocalDate manufactureDate;
    private String productTypeCode;
    private Integer weight;
    private Integer width;
    private Integer height;
    private String color;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductTypeCode() {
        return productTypeCode;
    }

    public void setProductTypeCode(String productTypeCode) {
        this.productTypeCode = productTypeCode;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public ProductSpecification extractProductSpecification(){
        ProductSpecification productSpecification=new ProductSpecification();
        productSpecification.setPrice(this.price);
        productSpecification.setName(this.name);
        productSpecification.setManufactureDate(this.manufactureDate);
        return productSpecification;
    }

    public DetailSpecification extractDetailSpecification(){
        DetailSpecification detailSpecification=new DetailSpecification();
        detailSpecification.setWeight(this.weight);
        detailSpecification.setHeight(this.height);
        detailSpecification.setWidth(this.width);
        detailSpecification.setColor(this.color);
        return detailSpecification;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateProductCommand that = (CreateProductCommand) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(manufactureDate, that.manufactureDate) &&
                Objects.equals(productTypeCode, that.productTypeCode) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(width, that.width) &&
                Objects.equals(height, that.height) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, manufactureDate, productTypeCode, weight, width, height, color);
    }
}
