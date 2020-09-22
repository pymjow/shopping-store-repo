package com.userservice.interfaces.rest.dto;

public class UserCreationResponse {

    private Long userId;

    public UserCreationResponse(Long userId) {
        this.userId = userId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
