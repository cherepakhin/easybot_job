package ru.perm.v.easybot.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupProductEntityTest {
    @Test
    void createSimple() {
        GroupProductEntity parent = new GroupProductEntity();
        GroupProductEntity groupProduct = new GroupProductEntity(100L, "GROUP100", true, -1L);

        assertEquals(100L, groupProduct.getId());
        assertEquals("GROUP100", groupProduct.getName());
        assertEquals(-1L, groupProduct.getParentId());
    }

    @Test
    void createDefault() {
        GroupProductEntity group = new GroupProductEntity();
        assertEquals(-1L, group.getId());
        assertEquals("", group.getName());
    }
}