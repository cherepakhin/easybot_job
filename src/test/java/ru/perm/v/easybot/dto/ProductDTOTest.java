package ru.perm.v.easybot.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDTOTest {

    @Test
    void testEquals() {
        Long ID = 1L;
        String NAME = "NAME";
        Long GROUP_ID = 100L;
        ProductDTO p1 = new ProductDTO(ID, NAME, GROUP_ID);
        ProductDTO p2 = new ProductDTO(ID, NAME, GROUP_ID);
        assertEquals(p1, p2);
    }

    @Test
    void testNotEquals() {
        Long ID = 1L;
        String NAME = "NAME";
        Long GROUP_ID = 100L;
        ProductDTO p1 = new ProductDTO(ID, NAME, GROUP_ID);
        ProductDTO p2 = new ProductDTO(ID + 1, NAME, GROUP_ID);
        assertNotEquals(p1, p2);
    }
}