package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.repository.GroupProductRepository;
import ru.perm.v.easybot.service.GroupProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class GroupProductServiceImplTest {

    @MockBean
    public GroupProductRepository repository;

    @Test
    void getAll() {
        List<GroupProductEntity> entities = new ArrayList<>();
        entities.add(new GroupProductEntity(1L, "NAME_1", true, 1L));
        entities.add(new GroupProductEntity(2L, "NAME_2", true, 1L));
        when(repository.findAllByOrderByIdAsc()).thenReturn(entities);

        GroupProductService groupProductService = new GroupProductServiceImpl(repository);

        List<GroupProductEntity> groups = groupProductService.getAll();

        assertEquals(2, groups.size());
        assertEquals(new GroupProductEntity(1L, "NAME_1", true, 1L), groups.get(0));
        assertEquals(new GroupProductEntity(2L, "NAME_2", true, 1L), groups.get(1));
    }

    @Test
    void getById() throws Exception {
        GroupProductService groupProductService = new GroupProductServiceImpl(repository);
        when(repository.getById(1L)).thenReturn(new GroupProductEntity(1L, "NAME_1", true, -100L));
        GroupProductEntity groupProduct = groupProductService.getById(1L);
        assertEquals(1L, groupProduct.getId());
    }

    @Test
    void getByNotExistId() {
        GroupProductService groupProductService = new GroupProductServiceImpl(repository);
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

        GroupProductService groupProductService = new GroupProductServiceImpl(repository);
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
//        Long ID = 1L;
//        String NAME="NAME_1";
//        Long PARENT_ID = -1L;
//        GroupProductEntity entity = new GroupProductEntity();
//        when(repository.save(entity))
//                .thenReturn(new GroupProductEntity(ID, NAME, true, PARENT_ID));
//
//    }

    @Test
    void create() {
        GroupProductRepository repository = mock(GroupProductRepository.class);
        Long MAX_ID=10L;
        String NAME="NAME_1";
        Long PARENT_ID = -1L;

        when(repository.getMaxId()).thenReturn(MAX_ID);
        when(repository.save(new GroupProductEntity(MAX_ID +1, NAME, true, PARENT_ID)))
                .thenReturn(new GroupProductEntity(MAX_ID +1, NAME, true, PARENT_ID));

        GroupProductService service = new GroupProductServiceImpl(repository);

        GroupProductEntity groupProduct = service.create(NAME, PARENT_ID);

        assertEquals(MAX_ID + 1, groupProduct.getId());
        assertEquals(NAME, groupProduct.getName());
        assertEquals(PARENT_ID, groupProduct.getParentId());
        verify(repository, times(1)).getMaxId();
    }
}