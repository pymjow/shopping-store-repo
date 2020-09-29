package com.accountservice.document.model.command;

public class CreateAccountCommand {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
