package com.userservice.document.model.entity;

import com.userservice.document.model.aggregates.Product;
import com.userservice.document.model.aggregates.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Purchase {

    private Long id;
    private User user;
    private Product product;
    private LocalDate creationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Embedded
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
