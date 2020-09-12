package com.oauth2.application.internal.outbound;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalUserDetailService implements UserDetailsService {

    private RestTemplate restTemplate;

    public ExternalUserDetailService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
