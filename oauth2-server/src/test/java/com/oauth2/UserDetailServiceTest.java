package com.oauth2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth2.application.outbound.ExternalUserDetailService;
import com.oauth2.application.outbound.dto.UserInquiryResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@RestClientTest(ExternalUserDetailService.class)
public class UserDetailServiceTest {

    @Autowired
    private ExternalUserDetailService externalUserDetailService;

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws JsonProcessingException {
        UserInquiryResponse userInquiryResponse=new UserInquiryResponse();
        userInquiryResponse.setEnabled(true);
        userInquiryResponse.setExpired(false);
        userInquiryResponse.setLocked(false);
        userInquiryResponse.setUsername("test");
        userInquiryResponse.setPassword("{bcrypt}$2a$10$eWJYngKQ5SwTOVZFlcWCrOQ3SpcALXgaqoLJZlptDqZtR7jil47Ne");
        userInquiryResponse.setAuthorities(Stream.of("READ","WRITE").collect(Collectors.toSet()));
        String inquryJson=objectMapper.writeValueAsString(userInquiryResponse);
        mockServer.expect(MockRestRequestMatchers.requestTo("/user-service/inquiry/username"))
                .andRespond(MockRestResponseCreators.withSuccess().contentType(MediaType.APPLICATION_JSON).body(inquryJson));

    }

    @Test
    public void testUserDetailService(){

        UserDetails userDetails=externalUserDetailService.loadUserByUsername("test");

        Assertions.assertThat(userDetails.getUsername()).isNotNull();
        Assertions.assertThat(userDetails.getPassword()).isNotNull();
        Assertions.assertThat(userDetails.getAuthorities()).isNotEmpty();


    }


}
