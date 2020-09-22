package com.userservice.application;

import com.userservice.application.internal.commandservice.UserCommandService;
import com.userservice.document.model.aggregates.User;
import com.userservice.document.model.commands.CreateUserCommand;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(UserCommandService.class)
public class UserCommnadServiceTest {

    @Autowired
    private UserCommandService userCommandService;

    @Test
    public void testCreateUser() {

        CreateUserCommand createUserCommand = new CreateUserCommand.Builder()
                .setUsername("milad")
                .setPassword("test")
                .setFirstName("test")
                .setLastName("test")
                .setBirthDate(LocalDate.now())
                .setMobile("+9898653786")
                .setEmail("test@gmail.com")
                .setAddress("test")
                .setZipCode("5458452150")
                .setRoles(Stream.of("READ", "WRITE").collect(Collectors.toSet()))
                .build();

        User user = userCommandService.createUser(createUserCommand);

        Assertions.assertThat(user.getId()).isNotNull();
        Assertions.assertThat(user.getUserProfile().getId()).isNotNull();
        Assertions.assertThat(user.getUserRoleList()).isNotEmpty();

    }

}
