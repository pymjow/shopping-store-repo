package com.userservice.interfaces.rest.transformer;

import com.userservice.document.model.commands.CreateUserCommand;
import com.userservice.interfaces.rest.dto.UserCreationRequest;

public class CreateUserDtoToCommandAssembler {

    public static CreateUserCommand toCreateUserCommand(UserCreationRequest request) {

        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.setAddress(request.getAddress());
        createUserCommand.setBirthDate(request.getBirthDate());
        createUserCommand.setEmail(request.getEmail());
        createUserCommand.setFirstName(request.getFirstName());
        createUserCommand.setLastName(request.getLastName());
        createUserCommand.setPassword(request.getPassword());
        createUserCommand.setUsername(request.getUsername());
        createUserCommand.setMobile(request.getMobile());
        createUserCommand.setZipCode(request.getZipCode());
        createUserCommand.setRoles(request.getRoles());
        return createUserCommand;

    }

}
