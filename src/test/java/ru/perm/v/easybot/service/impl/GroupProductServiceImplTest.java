package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.repository.GroupProductRepository;
import ru.perm.v.easybot.service.GroupProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class GroupProductServiceImplTest {

    GroupProductService groupProductService;
    GroupProductRepository repository;

    public GroupProductServiceImplTest() {
        repository = mock(GroupProductRepository.class);
        this.groupProductService = new GroupProductServiceImpl(repository);
    }

    @Test
    void getAll() {
        List<GroupProductEntity> entities = new ArrayList<>();
        entities.add(new GroupProductEntity(1L, "NAME_1",true,-100L));
        entities.add(new GroupProductEntity(2L, "NAME_2",true,-100L));
        when(repository.findAll()).thenReturn(entities);

        List<GroupProductEntity> groups = groupProductService.getAll();

        assertEquals(2, groups.size());
        assertEquals(new GroupProductEntity(1L, "NAME_1",true,-100L), groups.get(0));
        assertEquals(new GroupProductEntity(2L, "NAME_2",true,-100L), groups.get(1));
    }

    @Test
    void getById() throws Exception {
        GroupProductEntity entity =new GroupProductEntity(1L, "NAME_1",true,-100L);
        when(repository.getById(1L)).thenReturn(entity);
        GroupProductEntity groupProduct = groupProductService.getById(1L);
        assertEquals(1L, groupProduct.getId());
    }

    @Test
    void getByNotExistId() {
        assertThrows(Exception.class, () -> groupProductService.getById(-100L));
    }

}