package com.userservice.application.internal.queryservice;

import com.userservice.document.model.aggregates.User;
import com.userservice.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserQueryService {

    private final UserRepository userRepository;

    public UserQueryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new RuntimeException("user is not found");
        }

        return user.get();
    }


}
