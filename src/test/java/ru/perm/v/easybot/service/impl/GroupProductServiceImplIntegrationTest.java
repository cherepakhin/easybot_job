package ru.perm.v.easybot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.repository.GroupProductRepository;
import ru.perm.v.easybot.service.GroupProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupProductServiceImplIntegrationTest {


    @Test
    @Sql("/import_for_test_group.sql")
    void getAll(@Autowired GroupProductRepository repository) {

        GroupProductService groupProductService = new GroupProductServiceImpl(repository, null);

        List<GroupProductEntity> groups = groupProductService.getAll();

        assertEquals(6, groups.size());
        assertEquals(new GroupProductEntity(1L, "IT products", false, -1L), groups.get(0));
        assertEquals(new GroupProductEntity(2L, "Computers", false, 1L), groups.get(1));
        assertEquals(new GroupProductEntity(3L, "Desktop Computers", true, 2L), groups.get(2));
        assertEquals(new GroupProductEntity(4L, "Notebooks", true, 2L), groups.get(3));
    }

    @Test
    @Sql("/import_for_test_group.sql")
    void getById(@Autowired GroupProductRepository repository) throws Exception {
        GroupProductService groupProductService = new GroupProductServiceImpl(repository, null);
        GroupProductEntity groupProduct = groupProductService.getById(1L);
        assertEquals(1L, groupProduct.getId());
    }

    @Test
    @Sql("/import_for_test_group.sql")
    void getByNotExistId(@Autowired GroupProductRepository repository) {
        GroupProductService groupProductService = new GroupProductServiceImpl(repository, null);
        try {
            groupProductService.getById(-100L);
        } catch (Exception e) {
            assertTrue(true);
        }
        assertFalse(false);
    }

    @Test
    @Sql("/drop_all.sql")
    void dropAll(@Autowired GroupProductRepository repository) {
        GroupProductService groupProductService = new GroupProductServiceImpl(repository, null);
        assertEquals(0, groupProductService.getAll().size());
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

    // only for demo, study, check etc
    @Test
    @Sql("/import_for_test_group.sql")
    void findByParentId(@Autowired GroupProductRepository repository) {
        Long ID = 1L;
        List<GroupProductEntity> groups = repository.findByParentIdOrderByParentIdAscIdAsc(ID);
        for (GroupProductEntity g : groups) {
            System.out.println(g);
        }
        // Computers, Monitors, Hard drives
        assertEquals(3, groups.size());
    }
}