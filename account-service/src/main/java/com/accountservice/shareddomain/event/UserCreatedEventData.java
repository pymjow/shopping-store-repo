package com.accountservice.shareddomain.event;

public class UserCreatedEventData {

    private Long userId;

    public UserCreatedEventData(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}


