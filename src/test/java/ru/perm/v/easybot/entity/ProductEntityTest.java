package ru.perm.v.easybot.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductEntityTest {
    @Test
    void createSimple() {
        ProductEntity product = new ProductEntity(100L, "NAME100", 0L);
        assertEquals(100L, product.getId());
        assertEquals("NAME100", product.getName());
    }

    @Test
    void createDefault() {
        ProductEntity product = new ProductEntity();
        assertEquals(-1L, product.getId());
        assertEquals("", product.getName());
    }
}