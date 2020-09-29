package com.accountservice.shareddomain.event;

public class AccountCreatedEventData {

    private Long userId;

    public AccountCreatedEventData(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
