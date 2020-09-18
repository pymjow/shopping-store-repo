package com.accountservice.document.model.aggregates;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class User {

    @Column
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
