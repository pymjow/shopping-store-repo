package com.userservice.infrastructure;

import com.userservice.config.UtilConfig;
import com.userservice.document.model.aggregates.User;
import com.userservice.document.model.valueobjects.AccountState;
import com.userservice.document.model.valueobjects.UserCredentials;
import com.userservice.infrastructure.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(UtilConfig.class)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void testFetchAll() {

        List<User> userList = userRepository.findAll();
        Assertions.assertThat(userList.isEmpty()).isEqualTo(false);
    }

    @Test
    public void testSave() {

        AccountState accountState = new AccountState();
        accountState.setEnabled(true);
        accountState.setExpired(false);
        accountState.setLocked(false);

        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setPassword(passwordEncoder.encode("test"));
        userCredentials.setUsername("test");

        User user = new User();
        user.setId(3L);
        user.setAccountState(accountState);
        user.setUserCredentials(userCredentials);

        user = userRepository.save(user);

        Assertions.assertThat(user.getId()).isNotNull();
    }

    @Test
    public void testFetchByUsername(){

        String username="test";
        Optional<User> user=userRepository.findByUsername(username);
        Assertions.assertThat(user.isPresent()).isEqualTo(true);
        Assertions.assertThat(user.get().getUserRoleList().isEmpty()).isEqualTo(false);
    }

}
