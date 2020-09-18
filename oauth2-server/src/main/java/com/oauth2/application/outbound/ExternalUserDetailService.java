package com.oauth2.application.outbound;

import com.oauth2.application.outbound.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalUserDetailService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    public ExternalUserDetailService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto=restTemplate.getForObject("")

    }
}
