package com.accountservice.infrastructure;

import com.accountservice.document.model.aggregates.Account;
import com.accountservice.document.model.entity.Bill;
import com.accountservice.document.model.entity.PaymentType;
import com.accountservice.document.model.valueobjects.BillSummery;
import com.accountservice.document.model.valueobjects.Purchase;
import com.accountservice.infrastructure.repositories.AccountRepository;
import com.accountservice.infrastructure.repositories.BillRepository;
import com.accountservice.infrastructure.repositories.PaymentTypeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BillRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Test
    public void fetchTest() {
        Assertions.assertThat(billRepository.findAll().isEmpty()).isEqualTo(false);
    }

    @Test
    public void saveTest() {

        Account account = accountRepository.getOne(1L);

        BillSummery billSummery = new BillSummery();
        billSummery.setTotalAmount(100000.0);
        billSummery.setTotalCount(10000);

        PaymentType paymentType = paymentTypeRepository.getOne(1L);

        Purchase purchase = new Purchase();
        purchase.setPurchaseId(1L);

        Bill bill = new Bill();
        bill.setAccount(account);
        bill.setBillSummery(billSummery);
        bill.setPaymentType(paymentType);
        bill.setPurchase(purchase);

        bill = billRepository.save(bill);

        Assertions.assertThat(bill.getId()).isNotNull();

    }

}
