package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ResetDBServiceImplTest {

    @Autowired
    ResetDBServiceImpl resetDBService;

    @Test
    @Sql("/import_for_test_group")
    void resetProducts() throws Exception {
        resetDBService.reset();
    }
}