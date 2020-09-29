package com.accountservice.interfaces.kafka.transformer;

import com.accountservice.document.model.command.CreateAccountCommand;
import com.accountservice.shareddomain.event.UserCreatedEventData;

public class CreateEvent2AccountCommandAssembler {

    public static CreateAccountCommand toCreateAccount(UserCreatedEventData userCreatedEventData){
        CreateAccountCommand createAccountCommand=new CreateAccountCommand();
        createAccountCommand.setUserId(userCreatedEventData.getUserId());
        return createAccountCommand;
    }

}
