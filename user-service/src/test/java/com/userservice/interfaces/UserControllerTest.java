package com.userservice.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.userservice.application.internal.commandservice.UserCommandService;
import com.userservice.application.internal.queryservice.UserQueryService;
import com.userservice.config.ResourceServerConfig;
import com.userservice.document.model.aggregates.User;
import com.userservice.document.model.entity.UserRole;
import com.userservice.document.model.valueobjects.AccountState;
import com.userservice.document.model.valueobjects.UserCredentials;
import com.userservice.interfaces.rest.UserController;
import com.userservice.interfaces.rest.dto.UserCreationRequest;
import com.userservice.interfaces.rest.dto.UserInquiryRequest;
import com.userservice.interfaces.rest.transformer.CreateUserDtoToCommandAssembler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {


    private final String username = "test";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserQueryService userQueryService;

    @MockBean
    private UserCommandService userCommandService;


    private void setupFindByUsername() {
        User user = new User();
        user.setUserCredentials(new UserCredentials("test", "test"));
        user.setAccountState(new AccountState(true, false, false));
        UserRole userRole = new UserRole();
        userRole.setAuthority("test");
        user.setUserRoleList(Arrays.asList(userRole));
        Mockito.when(userQueryService.findByUsername(username)).thenReturn(user);
    }

    @Test
    @WithMockUser("test")
    public void testFindByUsername() throws Exception {

        setupFindByUsername();

        UserInquiryRequest userInquiryRequest = new UserInquiryRequest();
        userInquiryRequest.setUsername("test");
        String body = new ObjectMapper().writeValueAsString(userInquiryRequest);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/username")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.enabled").exists());
    }

    private void setupCreateUser() {
        User user = Mockito.mock(User.class);
        Mockito.when(user.getId()).thenReturn(1L);
        UserCreationRequest request = new UserCreationRequest();
        request.setAddress("test");
        request.setBirthDate(LocalDate.now());
        request.setEmail("test@gmail.com");
        request.setFirstName("test");
        request.setLastName("test");
        request.setMobile("+9898653786");
        request.setRoles(Stream.of("READ", "WRITE").collect(Collectors.toSet()));
        request.setZipCode("5458452150");
        request.setUsername("test");
        request.setPassword("test");
        Mockito.when(userCommandService.createUser(CreateUserDtoToCommandAssembler.toCreateUserCommand(request)))
                .thenReturn(user);
    }

    @Test
    @WithMockUser("test")
    public void testCreateUser() throws Exception {

        setupCreateUser();

        UserCreationRequest request = new UserCreationRequest();
        request.setAddress("test");
        request.setBirthDate(LocalDate.now());
        request.setEmail("test@gmail.com");
        request.setFirstName("test");
        request.setLastName("test");
        request.setMobile("+9898653786");
        request.setRoles(Stream.of("READ", "WRITE").collect(Collectors.toSet()));
        request.setZipCode("5458452150");
        request.setUsername("test");
        request.setPassword("test");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String body = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                .with(csrf())
                .content(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").exists());

    }





}
