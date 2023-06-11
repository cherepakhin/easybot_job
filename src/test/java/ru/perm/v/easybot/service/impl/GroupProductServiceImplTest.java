package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.easybot.service.GroupProductService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupProductServiceImplTest {

    @Autowired
    GroupProductService groupProductService;

    @Test
    void reset() {
        groupProductService.reset();
    }
}