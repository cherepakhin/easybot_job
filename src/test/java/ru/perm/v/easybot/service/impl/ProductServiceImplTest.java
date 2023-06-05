package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import ru.perm.v.easybot.entity.ProductEntity;
import ru.perm.v.easybot.repository.ProductRepository;
import ru.perm.v.easybot.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    ProductRepository repository = mock(ProductRepository.class);
    ProductService productService = new ProductServiceImpl(repository);

    @Test
    void getById() {
        Long ID = 10L;
        ProductEntity product = new ProductEntity();
        product.setId(ID);
        when(repository.getById(ID)).thenReturn(product);
        try {
            product = productService.getById(ID);
        } catch (Exception e) {
            fail();
        }
        assertEquals(ID, product.getId());
    }

    @Test
    void getAll() {
        Long ID_10 = 10L;
        Long ID_20 = 20L;
        ProductEntity product10 = new ProductEntity(ID_10, "", 100L);
        ProductEntity product20 = new ProductEntity(ID_20, "", 200L);
        List<ProductEntity> products = new ArrayList<>();
        products.add(product10);
        products.add(product20);

        when(repository.findAll(Sort.by("name"))).thenReturn(products);

        List<ProductEntity> received = new ArrayList<>();
        try {
            received = productService.getAll();
        } catch (Exception e) {
            fail();
        }
        assertEquals(2, received.size());
        assertEquals(ID_10, received.get(0).getId());
        assertEquals(ID_20, received.get(1).getId());
        assertEquals(100L, received.get(0).getGroupProductId());
    }
}