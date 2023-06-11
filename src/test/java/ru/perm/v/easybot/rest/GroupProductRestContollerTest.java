package ru.perm.v.easybot.rest;

import org.junit.jupiter.api.Test;
import ru.perm.v.easybot.dto.GroupProductDTO;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.rest.excpt.ResourceNotFoundException;
import ru.perm.v.easybot.service.GroupProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
        GroupProductEntity groupProduct = new GroupProductEntity(ID, NAME, false, PARENT_ID);

        when(groupProductService.getById(ID)).thenReturn(groupProduct);
        GroupProductRestContoller contoller = new GroupProductRestContoller(groupProductService);

        GroupProductDTO dto = contoller.getById(ID);

        assertEquals(new GroupProductDTO(ID, NAME, PARENT_ID, false), dto);
    }

    @Test
    void getByIdIfNull() throws Exception {
        Long ID = 1L;
        GroupProductService groupProductService = mock(GroupProductService.class);

        when(groupProductService.getById(ID)).thenReturn(null);
        GroupProductRestContoller contoller = new GroupProductRestContoller(groupProductService);
        assertThrows(ResourceNotFoundException.class, () -> contoller.getById(ID));
    }

    @Test
    void delete() throws Exception {
        Long ID = 1L;
        GroupProductService groupProductService = mock(GroupProductService.class);
        GroupProductRestContoller contoller = new GroupProductRestContoller(groupProductService);

        doThrow(new Exception()).when(groupProductService).delete(ID);

        assertThrows(Exception.class, () -> contoller.delete(ID));
    }

    @Test
    void create() throws Exception {
        Long ID = 1L;
        String NAME = "NAME_1";
        Long PARENT_ID = 1L;
        Boolean IS_LAST = true;
        GroupProductService groupProductService = mock(GroupProductService.class);
        GroupProductEntity groupProduct = new GroupProductEntity(ID, NAME, IS_LAST, PARENT_ID);

        when(groupProductService.create(NAME, PARENT_ID, IS_LAST)).thenReturn(groupProduct);
        GroupProductRestContoller contoller = new GroupProductRestContoller(groupProductService);

        GroupProductDTO dto = contoller.create(NAME, PARENT_ID, IS_LAST);

        assertEquals(new GroupProductDTO(ID, NAME, PARENT_ID, IS_LAST), dto);
    }

    @Test
    void update() throws Exception {
        Long ID = 1L;
        String NAME_OLD = "NAME_OLD";
        String NAME_NEW = "NAME_NEW";
        Long PARENT_ID = 1L;
        Boolean IS_LAST = true;

        GroupProductService mockGroupProductService = mock(GroupProductService.class);

        GroupProductEntity groupProductOld = new GroupProductEntity(ID, NAME_OLD, IS_LAST, PARENT_ID);
        when(mockGroupProductService.getById(ID)).thenReturn(groupProductOld);

        GroupProductEntity parentGroupProduct = new GroupProductEntity(PARENT_ID, "", false, -1L);
        when(mockGroupProductService.getById(PARENT_ID)).thenReturn(parentGroupProduct);

//        when(groupProductService.create(NAME_NEW, PARENT_ID, IS_LAST)).thenReturn(groupProductNew);

        GroupProductEntity groupProductNew = new GroupProductEntity(ID, NAME_NEW, IS_LAST, PARENT_ID);
        when(mockGroupProductService.save(ID, NAME_NEW, PARENT_ID, IS_LAST)).thenReturn(groupProductNew);

        GroupProductRestContoller contoller = new GroupProductRestContoller(mockGroupProductService);

        GroupProductDTO dto = contoller.update(ID, NAME_NEW, PARENT_ID, IS_LAST);

        assertEquals(new GroupProductDTO(ID, NAME_NEW, PARENT_ID, IS_LAST), dto);
    }

}