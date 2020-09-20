package com.userservice.interfaces.rest;

import com.userservice.application.internal.queryservice.UserQueryService;
import com.userservice.document.model.aggregates.User;
import com.userservice.interfaces.rest.dto.UserInquiryRequest;
import com.userservice.interfaces.rest.dto.UserInquiryResponse;
import com.userservice.interfaces.rest.exception.ErrorResponse;
import com.userservice.interfaces.rest.transformer.UserToInquiryResponseAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserQueryService userQueryService;

    public UserController(UserQueryService userQueryService){
        this.userQueryService=userQueryService;
    }

    @GetMapping("/username")
    public UserInquiryResponse inquiryByUsername(@Valid  UserInquiryRequest request, BindingResult result){

        if(result.hasErrors()){
            throw new RuntimeException(result.getAllErrors().get(0).getDefaultMessage());
        }

        User user= userQueryService.findByUsername(request.getUsername());

        return UserToInquiryResponseAssembler.toUserInquiryResponse(user);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handle(Exception e){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
