package com.userservice.interfaces.rest.dto;

import java.util.List;

public class ErrorResponse {

    private List<String> errorMessageList;

    public List<String> getErrorMessageList() {
        return errorMessageList;
    }

    public void setErrorMessageList(List<String> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }
}
