package com.productservice.document.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DetailSpecification {

    private Integer weight;
    private Integer width;
    private Integer Height;
    private String color;

    @Column
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Column
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Column
    public Integer getHeight() {
        return Height;
    }

    public void setHeight(Integer height) {
        Height = height;
    }

    @Column
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
