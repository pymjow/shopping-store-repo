package com.warehouseservice.infrastructure;

import com.warehouseservice.document.model.aggregates.Product;
import com.warehouseservice.document.model.aggregates.Warehouse;
import com.warehouseservice.document.model.entity.WarehouseProduct;
import com.warehouseservice.document.model.valueobjects.WarehouseProductSpecification;
import com.warehouseservice.infrastructure.repositories.WarehouseProductRepository;
import com.warehouseservice.infrastructure.repositories.WarehouseRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WarehouseProductRepositoryTest {

    @Autowired
    private WarehouseProductRepository warehouseProductRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Test
    public void testFetch() {

        Product product = new Product();
        product.setProductId(1L);

        Warehouse warehouse = warehouseRepository.getOne(1L);

        WarehouseProductSpecification warehouseProductSpecification = new WarehouseProductSpecification();
        warehouseProductSpecification.setCount(100);

        WarehouseProduct warehouseProduct = new WarehouseProduct();
        warehouseProduct.setProduct(product);
        warehouseProduct.setWarehouse(warehouse);
        warehouseProduct.setWarehouseProductSpecification(warehouseProductSpecification);

        warehouseProduct = warehouseProductRepository.save(warehouseProduct);

        Assertions.assertThat(warehouseProduct.getId()).isNotNull();

    }

}
