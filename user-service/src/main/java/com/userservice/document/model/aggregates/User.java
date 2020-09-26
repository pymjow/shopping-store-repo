package com.userservice.document.model.aggregates;

import com.userservice.document.model.commands.CreateUserCommand;
import com.userservice.document.model.entity.UserProfile;
import com.userservice.document.model.entity.UserRole;
import com.userservice.document.model.valueobjects.AccountState;
import com.userservice.document.model.valueobjects.UserCredentials;
import com.userservice.shareddomain.event.UserCreatedEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class User extends AbstractAggregateRoot<User> {

    private Long id;
    private UserProfile userProfile;
    private UserCredentials userCredentials;
    private AccountState accountState;
    private List<UserRole> userRoleList;

    public User() {

    }

    public User(CreateUserCommand createUserCommand) {

        this.setUserCredentials(createUserCommand.extractUserCredentials());
        this.setAccountState(new AccountState(true, false, false));
        List<UserRole> roles = createUserCommand.getRoles().stream().map(UserRole::new).collect(Collectors.toList());
        for (UserRole userRole : roles) {
            userRole.setUser(this);
        }
        this.setUserRoleList(roles);

        UserProfile profile = new UserProfile();
        profile.setPersonalInfo(createUserCommand.extractPersonInfo());
        profile.setLocation(createUserCommand.extractLocation());
        profile.setContact(createUserCommand.extractContact());
        profile.setUser(this);
        this.setUserProfile(profile);

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Embedded
    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    @Embedded
    public AccountState getAccountState() {
        return accountState;
    }

    public void setAccountState(AccountState accountState) {
        this.accountState = accountState;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }





}
