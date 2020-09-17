package com.productservice.document.model.aggregates;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class User {

    private Long userId;

    @Column
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
