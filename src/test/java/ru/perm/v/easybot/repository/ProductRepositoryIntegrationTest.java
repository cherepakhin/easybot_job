package ru.perm.v.easybot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.perm.v.easybot.entity.ProductEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ProductRepositoryIntegrationTest {

    @Autowired
    public ProductRepository repository;

    @Test
    @Sql({"/insert_desktop.sql"})
    void createProduct() {
        Long ID = repository.getMaxId() + 1;
        Long GROUP_ID = 3L;
        String NAME = "NAME";

        ProductEntity product = new ProductEntity(ID, NAME, GROUP_ID);
        ProductEntity saved = repository.save(product);
        assertEquals(new ProductEntity(ID, NAME, GROUP_ID), saved);
    }

    @Test
    @Sql({"/insert_desktop.sql"})
    void findByGroupProductIdOrderByIdAsc() {
        List<ProductEntity> desktops = repository.findByGroupProductIdOrderByIdAsc(3L);
        assertEquals(2, desktops.size());
        assertEquals("Desktop31", desktops.get(0).getName());
        assertEquals("Desktop32", desktops.get(1).getName());
    }

}