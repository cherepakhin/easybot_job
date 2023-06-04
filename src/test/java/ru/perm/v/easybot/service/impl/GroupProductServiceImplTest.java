package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.service.GroupProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupProductServiceImplTest {

    @Autowired
    GroupProductService groupProductService;

    @Test
    void getAll() {
        List<GroupProductEntity> groups = groupProductService.getAll();
        assertEquals(4, groups.size());
    }
}