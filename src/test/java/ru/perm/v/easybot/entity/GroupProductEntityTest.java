package ru.perm.v.easybot.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupProductEntityTest {
    @Test
    void createSimple() {
        Long PARENT_ID = -1L;
        GroupProductEntity groupProduct = new GroupProductEntity(100L, "GROUP100", true, PARENT_ID);

        assertEquals(100L, groupProduct.getId());
        assertEquals("GROUP100", groupProduct.getName());
        assertEquals(PARENT_ID, groupProduct.getParentId());
    }

    @Test
    void createDefault() {
        GroupProductEntity group = new GroupProductEntity();
        assertEquals(-1L, group.getId());
        assertEquals("", group.getName());
    }
}