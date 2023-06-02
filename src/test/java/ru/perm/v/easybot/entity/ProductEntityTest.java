package ru.perm.v.easybot.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityTest {
    @Test
    void createSimple() {
        ProductEntity product = new ProductEntity(100L, "NAME100");
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