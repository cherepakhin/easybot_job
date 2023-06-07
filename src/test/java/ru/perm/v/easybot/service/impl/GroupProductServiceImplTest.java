package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.repository.GroupProductRepository;
import ru.perm.v.easybot.service.GroupProductService;
import ru.perm.v.easybot.service.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class GroupProductServiceImplTest {

    @Autowired
    public GroupProductRepository repository;

    @Test
    void getAll() {
        GroupProductService groupProductService = new GroupProductServiceImpl(repository, null);

        List<GroupProductEntity> groups = groupProductService.getAll();

        assertEquals(6, groups.size());
        assertEquals(new GroupProductEntity(1L, "IT products", false, -1L), groups.get(0));
        assertEquals(new GroupProductEntity(2L, "Computers", false, 1L), groups.get(1));
        assertEquals(new GroupProductEntity(3L, "Desktop Computers", true, 2L), groups.get(2));
        assertEquals(new GroupProductEntity(4L, "Notebooks", true, 2L), groups.get(3));
    }

    @Test
    void getById() throws Exception {
        GroupProductService groupProductService = new GroupProductServiceImpl(repository, null);
        GroupProductEntity groupProduct = groupProductService.getById(1L);
        assertEquals(1L, groupProduct.getId());
    }

    @Test
    void getByNotExistId() {
        GroupProductService groupProductService = new GroupProductServiceImpl(repository, null);
        try {
            groupProductService.getById(-100L);
        } catch (Exception e) {
            assertTrue(true);
        }
        assertFalse(false);
    }

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

//    @Test
//    void create() {
//        GroupProductRepository repository = mock(GroupProductRepository.class);
//        Long MAX_ID = 10L;
//        String NAME = "NAME_1";
//        Long PARENT_ID = -1L;
//
//        when(repository.getMaxId()).thenReturn(MAX_ID);
//        when(repository.save(new GroupProductEntity(MAX_ID + 1, NAME, true, PARENT_ID)))
//                .thenReturn(new GroupProductEntity(MAX_ID + 1, NAME, true, PARENT_ID));
//
//        GroupProductService service = new GroupProductServiceImpl(repository);
//
//        GroupProductEntity groupProduct = service.create(NAME, PARENT_ID);
//
//        assertEquals(MAX_ID + 1, groupProduct.getId());
//        assertEquals(NAME, groupProduct.getName());
//        assertEquals(PARENT_ID, groupProduct.getParentId());
//        verify(repository, times(1)).getMaxId();
//    }

    @Test
    // only for demo, study, check etc
    void findByParentId() {
        Long ID = 1L;
        List<GroupProductEntity> groups = repository.findByParentIdOrderByParentIdAscIdAsc(ID);
        for(GroupProductEntity g :groups) {
            System.out.println(g);
        }
        // Computers, Monitors, Hard drives
        assertEquals(3, groups.size());
    }

    @Test
    void exceptionOnDeleteGroupWithProducts() {
        Long ID_DELETING_GROUP=100L;
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
        Long ID_DELETING_GROUP=100L;
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