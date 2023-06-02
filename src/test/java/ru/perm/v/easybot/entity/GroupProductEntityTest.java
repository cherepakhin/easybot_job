package ru.perm.v.easybot.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupProductEntityTest {
    @Test
    void createSimple() {
        GroupProductEntity groupProduct = new GroupProductEntity(100L, "GROUP100", true);
        assertEquals(100L, groupProduct.getId());
        assertEquals("GROUP100", groupProduct.getName());
        assertTrue(groupProduct.getIsLast());
    }

    @Test
    void createDefault() {
        GroupProductEntity group = new GroupProductEntity();
        assertEquals(-1L, group.getId());
        assertEquals("", group.getName());
        assertEquals(false, group.getIsLast());
    }

    @Test
    void createLast() {
        GroupProductEntity group = new GroupProductEntity();
        group.setIsLast(true);
        assertTrue(group.getIsLast());
    }
}