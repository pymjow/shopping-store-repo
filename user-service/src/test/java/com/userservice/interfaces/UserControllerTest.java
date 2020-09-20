package com.userservice.interfaces;

import com.userservice.application.internal.queryservice.UserQueryService;
import com.userservice.document.model.aggregates.User;
import com.userservice.document.model.entity.UserRole;
import com.userservice.document.model.valueobjects.AccountState;
import com.userservice.document.model.valueobjects.UserCredentials;
import com.userservice.interfaces.rest.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private final String username="test";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserQueryService userQueryService;

    @Before
    public void setup(){
        User user=new User();
        user.setUserCredentials(new UserCredentials("test","test"));
        user.setAccountState(new AccountState(true,false,false));
        UserRole userRole=new UserRole();
        userRole.setAuthority("test");
        user.setUserRoleList(Arrays.asList(userRole));
        Mockito.when(userQueryService.findByUsername(username)).thenReturn(user);
    }

    @Test
    @WithMockUser("test")
    public void testFindByUsername() throws Exception {

        MultiValueMap<String,String> params=new LinkedMultiValueMap<>();
        params.add("username",username);
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/users/username").params(params))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }





}
