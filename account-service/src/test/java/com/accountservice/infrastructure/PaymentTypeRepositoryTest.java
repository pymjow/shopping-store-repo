package com.accountservice.infrastructure;

import com.accountservice.infrastructure.repositories.PaymentTypeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentTypeRepositoryTest {

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Test
    public void testFetch() {
        Assertions.assertThat(paymentTypeRepository.findAll().isEmpty()).isEqualTo(false);
    }


}
