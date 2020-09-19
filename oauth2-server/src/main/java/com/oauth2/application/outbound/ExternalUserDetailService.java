package com.oauth2.application.outbound;

import com.oauth2.application.outbound.dto.UserInquiryRequest;
import com.oauth2.application.outbound.dto.UserInquiryResponse;
import com.oauth2.application.outbound.transformer.UserDtoToUserDetailAssembler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalUserDetailService implements UserDetailsService {

    private RestTemplate restTemplate;

    public ExternalUserDetailService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate=restTemplateBuilder.build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInquiryRequest request=new UserInquiryRequest();
        request.setUsername(username);

        UserInquiryResponse userInquiryResponse=
                restTemplate.getForObject("/user-service/inquiry/username",UserInquiryResponse.class,request);

        return UserDtoToUserDetailAssembler.toUserDetails(userInquiryResponse);

    }
}
