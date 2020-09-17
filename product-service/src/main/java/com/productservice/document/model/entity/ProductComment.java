package com.productservice.document.model.entity;

import com.productservice.document.model.aggregates.Product;
import com.productservice.document.model.aggregates.User;

import javax.persistence.*;

@Entity
public class ProductComment {

    private Long id;
    private User user;
    private Product product;
    private String message;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
