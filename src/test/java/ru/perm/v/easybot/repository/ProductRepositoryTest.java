package ru.perm.v.easybot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.easybot.entity.ProductEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    public ProductRepository repository;

    @Test
    void findByGroupProductIdOrderByIdAsc() {
        List<ProductEntity> desktops = repository.findByGroupProductIdOrderByIdAsc(3L);
        assertEquals(3, desktops.size());
        assertEquals("Desktop1", desktops.get(0).getName());
        assertEquals("Desktop2", desktops.get(1).getName());
        assertEquals("Desktop3", desktops.get(2).getName());
    }
}