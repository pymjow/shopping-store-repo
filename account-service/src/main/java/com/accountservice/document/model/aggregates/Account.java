package com.accountservice.document.model.aggregates;

import com.accountservice.document.model.command.CreateAccountCommand;
import com.accountservice.document.model.valueobjects.AccountDetail;
import com.accountservice.shareddomain.event.AccountCreatedEvent;
import com.accountservice.shareddomain.event.AccountCreatedEventData;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

@Entity
public class Account extends AbstractAggregateRoot<Account> {

    private Long id;
    private User user;
    private AccountDetail accountDetail;

    public Account(){

    }

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

    public Account(CreateAccountCommand userCreatedEventData){

        User user=new User();
        user.setUserId(userCreatedEventData.getUserId());
        this.setUser(user);
        this.setAccountDetail(new AccountDetail());

    }

}
