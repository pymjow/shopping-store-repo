package com.userservice.interfaces.rest.transformer;

import com.userservice.document.model.aggregates.User;
import com.userservice.document.model.entity.UserRole;
import com.userservice.interfaces.rest.dto.UserInquiryResponse;

import java.util.stream.Collectors;

public class UserToInquiryResponseAssembler {

    public static UserInquiryResponse toUserInquiryResponse(User user) {

        UserInquiryResponse userInquiryResponse = new UserInquiryResponse();
        userInquiryResponse.setEnabled(user.getAccountState().getEnabled());
        userInquiryResponse.setLocked(user.getAccountState().getLocked());
        userInquiryResponse.setExpired(user.getAccountState().getExpired());
        userInquiryResponse.setUsername(user.getUserCredentials().getUsername());
        userInquiryResponse.setPassword(user.getUserCredentials().getPassword());
        userInquiryResponse.setAuthorities(user.getUserRoleList().stream().map(UserRole::getAuthority).collect(Collectors.toSet()));
        return userInquiryResponse;

    }

}
