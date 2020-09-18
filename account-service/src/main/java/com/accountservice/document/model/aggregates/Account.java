package com.accountservice.document.model.aggregates;

import com.accountservice.document.model.valueobjects.AccountDetail;

import javax.persistence.*;

@Entity
public class Account {

    private Long id;
    private User user;
    private AccountDetail accountDetail;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Embedded
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Embedded
    public AccountDetail getAccountDetail() {
        return accountDetail;
    }

    public void setAccountDetail(AccountDetail accountDetail) {
        this.accountDetail = accountDetail;
    }
}
