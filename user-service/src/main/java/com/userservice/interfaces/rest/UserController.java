package com.userservice.interfaces.rest;

import com.userservice.application.internal.commandservice.UserCommandService;
import com.userservice.application.internal.queryservice.UserQueryService;
import com.userservice.document.model.aggregates.User;
import com.userservice.interfaces.rest.dto.*;
import com.userservice.interfaces.rest.exception.ValidationException;
import com.userservice.interfaces.rest.transformer.CreateUserDtoToCommandAssembler;
import com.userservice.interfaces.rest.transformer.UserToInquiryResponseAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UserController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @GetMapping("/username")
    public UserInquiryResponse inquiryByUsername(@RequestBody @Valid UserInquiryRequest request) {


        User user = userQueryService.findByUsername(request.getUsername());

        return UserToInquiryResponseAssembler.toUserInquiryResponse(user);
    }

    @PostMapping("/create")
    public UserCreationResponse createUser(@RequestBody @Valid UserCreationRequest request, BindingResult result) {
        User user = userCommandService.createUser(CreateUserDtoToCommandAssembler.toCreateUserCommand(request));
        return new UserCreationResponse(user.getId());
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ErrorResponse> handle(ValidationException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessageList(e.getMessages());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessageList(Stream.of(e.getLocalizedMessage()).collect(Collectors.toList()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
