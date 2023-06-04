package ru.perm.v.easybot.rest;

import org.junit.jupiter.api.Test;
import ru.perm.v.easybot.dto.GroupProductDTO;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.service.GroupProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GroupProductRestContollerTest {

    @Test
    void echo() {
        GroupProductService groupProductService = mock(GroupProductService.class);
        GroupProductRestContoller contoller = new GroupProductRestContoller(groupProductService);
        assertEquals("Ok", contoller.echo());
    }

    @Test
    void getAll() {
        GroupProductService groupProductService = mock(GroupProductService.class);
        List<GroupProductEntity> groups = new ArrayList<>();
        groups.add(new GroupProductEntity(1L, "NAME_1", true, -1L));
        when(groupProductService.getAll()).thenReturn(groups);
        GroupProductRestContoller contoller = new GroupProductRestContoller(groupProductService);
        List<GroupProductDTO> dtos = contoller.getAll();
        assertEquals(1, dtos.size());
    }

    @Test
    void getById() throws Exception {
        Long ID = 1L;
        String NAME = "NAME_1";
        Long PARENT_ID = 1L;
        GroupProductService groupProductService = mock(GroupProductService.class);
        GroupProductEntity groupProduct = new GroupProductEntity();
        groupProduct.setId(ID);
        groupProduct.setName(NAME);
        groupProduct.setParentId(PARENT_ID);

        when(groupProductService.getById(ID)).thenReturn(groupProduct);
        GroupProductRestContoller contoller = new GroupProductRestContoller(groupProductService);

        GroupProductDTO dto = contoller.getById(ID);

        assertEquals(new GroupProductDTO(ID, NAME, PARENT_ID), dto);
    }
}