package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.repository.GroupProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class GroupProductCacheMockTest {
    private GroupProductServiceImpl groupProductService;
    @Autowired
    CacheManager cacheManager;

    @Test
    void verifyCacheGetById(@Mock GroupProductRepository repository) throws Exception {
        Long ID_GROUP = 100L;
        Long ID_PARENT_GROUP = 101L;

        GroupProductEntity groupProduct = new GroupProductEntity(ID_GROUP, "", true, ID_PARENT_GROUP);

        GroupProductServiceImpl groupProductService = new GroupProductServiceImpl(repository, null);
        when(repository.getById(ID_GROUP)).thenReturn(groupProduct);
        GroupProductEntity group1;
        groupProductService.getById(ID_GROUP);
        groupProductService.getById(ID_GROUP);
        groupProductService.getById(ID_GROUP);
        group1 = groupProductService.getById(ID_GROUP);
        assertEquals(ID_GROUP, group1.getId());
        verify(repository, times(4)).getById(ID_GROUP);
    }

}
