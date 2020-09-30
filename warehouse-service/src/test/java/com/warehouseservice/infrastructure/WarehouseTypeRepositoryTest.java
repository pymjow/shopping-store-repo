package com.warehouseservice.infrastructure;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WarehouseTypeRepositoryTest {

    @Autowired
    private WarehouseTypeRepository warehouseTypeRepository;

    @Test
    public void testFetch() {
        Assertions.assertThat(warehouseTypeRepository.findAll().isEmpty()).isEqualTo(false);
    }

}
