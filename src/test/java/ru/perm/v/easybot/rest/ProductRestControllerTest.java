package ru.perm.v.easybot.rest;

import org.junit.jupiter.api.Test;
import ru.perm.v.easybot.entity.ProductEntity;
import ru.perm.v.easybot.service.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductRestControllerTest {

    @Test
    void getAll() throws Exception {
        ProductService service = mock(ProductService.class);
        ProductRestController controller = new ProductRestController(service);
        Long ID_1 = 1L;
        Long ID_2 = 2L;
        try {
            when(service.getAll()).thenReturn(List.of(new ProductEntity(ID_1,""),new ProductEntity(ID_2,"")));
        } catch (Exception e) {
            fail();
        }
        assertEquals(2, controller.getAll().size());
    }
}