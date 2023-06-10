package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.repository.GroupProductRepository;
import ru.perm.v.easybot.service.GroupProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupProductCacheMockTest {
    @Mock
    public GroupProductRepository repository;
    @InjectMocks
    GroupProductServiceImpl groupProductService;
    @Autowired
    CacheManager cacheManager;

    @Test
    void verifyCacheGetById() throws Exception {
        Long ID_GROUP = 100L;
        Long ID_PARENT_GROUP = 101L;

        GroupProductEntity groupProduct = new GroupProductEntity(ID_GROUP, "", true, ID_PARENT_GROUP);

        when(repository.getById(ID_GROUP)).thenReturn(groupProduct);

//        GroupProductService groupProductService = new GroupProductServiceImpl(repository, null);
        GroupProductEntity group1;
        group1 = groupProductService.getById(ID_GROUP);
        group1 = groupProductService.getById(ID_GROUP);
        assertEquals(ID_GROUP, group1.getId());
        verify(repository, times(2)).getById(ID_GROUP);
//        GroupProductEntity TEST_GROUP= new GroupProductEntity();
//        TEST_GROUP.setId(ID_GROUP);
//        GroupProductRepository repository = mock(GroupProductRepository.class);
//
//        GroupProductService groupProductService = new GroupProductServiceImpl(repository, null);
//        when(repository.getById(ID_GROUP))
//                .thenReturn(TEST_GROUP);
//
//        GroupProductEntity group1 = groupProductService.getById(ID_GROUP);
//        group1 = groupProductService.getById(ID_GROUP);
//        group1 = groupProductService.getById(ID_GROUP);
//        group1 = groupProductService.getById(ID_GROUP);
//        group1 = groupProductService.getById(ID_GROUP);
////        assertEquals(group1, group2);
//        verify(repository, times(1)).getById(ID_GROUP);
    }

}
