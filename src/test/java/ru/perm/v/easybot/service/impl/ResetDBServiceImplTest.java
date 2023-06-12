package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.service.GroupProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ResetDBServiceImplTest {

    @Autowired
    ResetDBServiceImpl resetDBService;

    @Autowired
    GroupProductService groupProductService;

    @Test
    @Sql("/import_for_test_group.sql")
    void resetProducts() throws Exception {
        List<GroupProductEntity> groups = groupProductService.getAll();
        assertEquals(6, groups.size());
        resetDBService.reset();
        groups = groupProductService.getAll();
        assertEquals(1, groups.size());
    }
}