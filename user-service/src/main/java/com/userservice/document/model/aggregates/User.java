package com.userservice.document.model.aggregates;

import com.userservice.document.model.entity.UserProfile;
import com.userservice.document.model.entity.UserRole;
import com.userservice.document.model.valueobjects.AccountState;
import com.userservice.document.model.valueobjects.UserCredentials;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    private Long id;
    private UserProfile userProfile;
    private UserCredentials userCredentials;
    private AccountState accountState;
    private List<UserRole> userRoleList;


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

    @OneToMany(mappedBy = "user")
    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }
}
