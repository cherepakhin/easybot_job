package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import ru.perm.v.easybot.entity.ProductEntity;
import ru.perm.v.easybot.repository.ProductRepository;
import ru.perm.v.easybot.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    ProductRepository repository = mock(ProductRepository.class);

    @Test
    void getById() {
        Long ID = 10L;
        ProductEntity product = new ProductEntity();
        product.setId(ID);
        when(repository.findById(ID)).thenReturn(Optional.of(product));
        ProductService productService = new ProductServiceImpl(repository);
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
        ProductService productService = new ProductServiceImpl(repository);
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

    @Test
    void create() {
        String NAME = "PRODUCT";
        Long GROUP_ID = 10L;
        ProductEntity mockProduct = new ProductEntity(201L, NAME, GROUP_ID);
        when(repository.getMaxId()).thenReturn(200L);
        when(repository.save(new ProductEntity(201L, NAME, GROUP_ID))).thenReturn(mockProduct);
        ProductEntity product = null;
        ProductService productService = new ProductServiceImpl(repository);

        try {
            product = productService.create(NAME, GROUP_ID);
        } catch (Exception e) {
            fail();
        }

        assertEquals(201L, product.getId());
        assertEquals(NAME, product.getName());
        assertEquals(GROUP_ID, product.getGroupProductId());
    }

    @Test
    void update() {
        Long ID = 1L;
        String NAME = "PRODUCT";
        Long GROUP_ID = 10L;
        ProductEntity product = new ProductEntity(ID, NAME, GROUP_ID);
        ProductEntity savedProduct = new ProductEntity(ID, "SAVED_" + NAME, GROUP_ID);

        when(repository.findById(ID)).thenReturn(Optional.of(product));
        when(repository.save(product)).thenReturn(savedProduct);

        ProductService productService = new ProductServiceImpl(repository);

        try {
            productService.update(product);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        assertEquals("SAVED_" + NAME, savedProduct.getName());
        assertEquals(ID, savedProduct.getId());
        assertEquals(GROUP_ID, savedProduct.getGroupProductId());
    }
}