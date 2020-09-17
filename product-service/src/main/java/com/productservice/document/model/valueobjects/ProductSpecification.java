package com.productservice.document.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class ProductSpecification {

    private String name;
    private Double price;
    private LocalDate manufactureDate;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column
    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }
}
