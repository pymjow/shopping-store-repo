package com.accountservice.application.commandservice;

import com.accountservice.document.model.aggregates.Account;
import com.accountservice.document.model.command.CreateAccountCommand;
import com.accountservice.infrastructure.repositories.AccountRepository;
import com.accountservice.shareddomain.event.AccountCreatedEvent;
import com.accountservice.shareddomain.event.AccountCreatedEventData;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountCommandService {

    private AccountRepository accountRepository;
    private ApplicationEventPublisher applicationEventPublisher;

    public AccountCommandService(AccountRepository accountRepository,ApplicationEventPublisher applicationEventPublisher){
        this.accountRepository=accountRepository;
        this.applicationEventPublisher=applicationEventPublisher;
    }

    @Transactional(rollbackFor = Exception.class)
    public Account createAccount(CreateAccountCommand createAccountCommand){
        Account account=new Account(createAccountCommand);
        account =accountRepository.save(account);
        applicationEventPublisher.publishEvent(new AccountCreatedEvent(new AccountCreatedEventData(createAccountCommand.getUserId())));

        return account;
    }

}
