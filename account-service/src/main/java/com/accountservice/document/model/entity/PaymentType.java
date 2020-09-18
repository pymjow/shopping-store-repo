package com.accountservice.document.model.entity;

import javax.persistence.*;

@Entity
public class PaymentType {

    private Long id;
    private String name;
    private String code;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
