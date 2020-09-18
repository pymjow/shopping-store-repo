package com.accountservice.infrastructure;

import com.accountservice.document.model.aggregates.Account;
import com.accountservice.document.model.aggregates.User;
import com.accountservice.document.model.valueobjects.AccountDetail;
import com.accountservice.infrastructure.repositories.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testFetch() {
        Assertions.assertThat(accountRepository.findAll().isEmpty()).isEqualTo(false);
    }

    @Test
    public void testSave() {

        User user = new User();
        user.setUserId(1L);

        AccountDetail detail = new AccountDetail();
        detail.setLocked(false);

        Account account = new Account();
        account.setUser(user);
        account.setAccountDetail(detail);

        account = accountRepository.save(account);

        Assertions.assertThat(account.getId()).isNotNull();
    }

}
