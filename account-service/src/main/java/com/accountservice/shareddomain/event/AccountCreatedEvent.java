package com.accountservice.shareddomain.event;

import org.springframework.context.ApplicationEvent;

public class AccountCreatedEvent extends ApplicationEvent {

    public AccountCreatedEvent(Object source) {
        super(source);
    }
}
