package com.warehouseservice.infrastructure;

import com.warehouseservice.document.model.aggregates.Warehouse;
import com.warehouseservice.document.model.valueobjects.WareHouseSpecification;
import com.warehouseservice.infrastructure.repositories.WarehouseRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class WarehouseRepositoryTest {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private WarehouseTypeRepository warehouseTypeRepository;

    @Test
    public void fetchTest() {
        Assertions.assertThat(warehouseRepository.findAll().isEmpty()).isEqualTo(false);
    }

    @Test
    public void saveTest() {

        WarehouseType warehouseType = warehouseTypeRepository.getOne(1L);

        WareHouseSpecification wareHouseSpecification = new WareHouseSpecification();
        wareHouseSpecification.setCapacity(100);
        wareHouseSpecification.setName("test");
        wareHouseSpecification.setWarehouseNumber("33232");

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseType(warehouseType);
        warehouse.setWareHouseSpecification(wareHouseSpecification);

        warehouse = warehouseRepository.save(warehouse);

        Assertions.assertThat(warehouse.getId()).isNotNull();


    }

}
