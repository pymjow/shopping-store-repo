package com.userservice.application.internal.commandservice;

import com.userservice.document.model.aggregates.User;
import com.userservice.document.model.commands.CreateUserCommand;
import com.userservice.document.model.entity.UserProfile;
import com.userservice.infrastructure.repositories.UserProfileRepository;
import com.userservice.infrastructure.repositories.UserRepository;
import com.userservice.shareddomain.event.UserCreatedEvent;
import com.userservice.shareddomain.event.UserCreatedEventData;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private ApplicationEventPublisher applicationEventPublisher;

    public UserCommandService(UserRepository userRepository, UserProfileRepository userProfileRepository,ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.applicationEventPublisher=applicationEventPublisher;
    }

    public User createUser(CreateUserCommand createUserCommand) {

        User user = new User(createUserCommand);
        UserProfile userProfile = user.getUserProfile();
        user = userRepository.save(user);
        userProfile.setId(user.getId());
        userProfile = userProfileRepository.save(userProfile);
        user.setUserProfile(userProfile);
        applicationEventPublisher.publishEvent(new UserCreatedEvent(new UserCreatedEventData(user.getId())));
        return user;
    }

}
