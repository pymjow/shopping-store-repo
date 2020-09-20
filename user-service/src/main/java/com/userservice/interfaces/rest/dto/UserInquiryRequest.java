package com.userservice.interfaces.rest.dto;

import javax.validation.constraints.NotEmpty;

public class UserInquiryRequest {

    @NotEmpty
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
