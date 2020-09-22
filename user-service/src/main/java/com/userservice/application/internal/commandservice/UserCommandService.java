package com.userservice.application.internal.commandservice;

import com.userservice.document.model.aggregates.User;
import com.userservice.document.model.commands.CreateUserCommand;
import com.userservice.document.model.entity.UserProfile;
import com.userservice.infrastructure.repositories.UserProfileRepository;
import com.userservice.infrastructure.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserCommandService(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }


    public User createUser(CreateUserCommand createUserCommand) {

        User user = new User(createUserCommand);
        UserProfile userProfile = user.getUserProfile();
        user = userRepository.save(user);
        userProfile.setId(user.getId());
        userProfile = userProfileRepository.save(userProfile);
        user.setUserProfile(userProfile);
        return user;
    }

}
