package com.userservice.application;

import com.userservice.application.internal.queryservice.UserQueryService;
import com.userservice.document.model.aggregates.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(UserQueryService.class)
public class UserQueryServiceTest {

    @Autowired
    private UserQueryService userQueryService;

    @Test
    public void testFindByUsername() {

        String username = "test";
        User user = userQueryService.findByUsername(username);
        Assertions.assertThat(user).isNotNull();
    }


}
