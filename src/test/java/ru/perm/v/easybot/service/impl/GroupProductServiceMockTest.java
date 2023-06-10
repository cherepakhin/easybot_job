package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.repository.GroupProductRepository;
import ru.perm.v.easybot.service.GroupProductService;
import ru.perm.v.easybot.service.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class GroupProductServiceMockTest {

    @Test
    void save() throws Exception {
        GroupProductRepository repository = mock(GroupProductRepository.class);

        GroupProductService groupProductService = new GroupProductServiceImpl(repository, null);
        when(repository.save(new GroupProductEntity(1L, "NAME_1", true, -100L)))
                .thenReturn(new GroupProductEntity(1L, "NAME_1", true, -100L));

        GroupProductEntity saved = groupProductService.save(
                new GroupProductEntity(1L, "NAME_1", true, -100L));


        assertEquals(new GroupProductEntity(1L, "NAME_1", true, -100L), saved);
        verify(repository, times(1))
                .save(new GroupProductEntity(1L, "NAME_1", true, -100L));
    }

    @Test
    void exceptionOnDeleteGroupWithProducts() {
        Long ID_DELETING_GROUP = 100L;
        GroupProductRepository repository = mock(GroupProductRepository.class);

        GroupProductService groupProductService = new GroupProductServiceImpl(repository, null);
        when(repository.findByParentIdOrderByParentIdAsc(ID_DELETING_GROUP))
                .thenReturn(List.of(new GroupProductEntity()));

        boolean isError = true;
        try {
            groupProductService.delete(ID_DELETING_GROUP);
        } catch (Exception e) {
            isError = false;
        }
        assertFalse(isError);
        verify(repository, times(1))
                .getById(ID_DELETING_GROUP);
    }

    @Test
    void exceptionOnDeleteGroupWithSubGroups() {
        Long ID_DELETING_GROUP = 100L;
        GroupProductRepository repository = mock(GroupProductRepository.class);
        ProductService productService = mock(ProductService.class);

        GroupProductService groupProductService = new GroupProductServiceImpl(repository, productService);
        when(repository.findByParentIdOrderByParentIdAsc(ID_DELETING_GROUP))
                .thenReturn(List.of(new GroupProductEntity()));

        boolean isError = true;
        try {
            groupProductService.delete(ID_DELETING_GROUP);
        } catch (Exception e) {
            isError = false;
        }
        assertFalse(isError);
        verify(repository, times(1))
                .getById(ID_DELETING_GROUP);
    }

    @Test
    void getMaxId() {
        GroupProductRepository repository = mock(GroupProductRepository.class);
        ProductService productService = mock(ProductService.class);

        Long MAX_ID = 100L;

        GroupProductService groupProductService = new GroupProductServiceImpl(repository, productService);
        when(repository.getMaxId()).thenReturn(MAX_ID);

        assertEquals(100L, groupProductService.getMaxId());
    }

}
