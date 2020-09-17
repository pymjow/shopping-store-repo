package com.userservice.infrastructure;

import com.userservice.document.model.aggregates.Product;
import com.userservice.document.model.aggregates.User;
import com.userservice.document.model.entity.Purchase;
import com.userservice.infrastructure.repositories.PurchaseRepository;
import com.userservice.infrastructure.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PurchaseTest {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFetchAll() {

        List<Purchase> purchaseList = purchaseRepository.findAll();
        Assertions.assertThat(purchaseList.isEmpty()).isEqualTo(false);
    }

    @Test
    public void savePurchase() {

        Product product = new Product();
        product.setProductId(1L);

        User user = userRepository.getOne(1L);

        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        purchase.setUser(user);

        purchase = purchaseRepository.save(purchase);

        Assertions.assertThat(purchase.getId()).isNotNull();

    }


}
