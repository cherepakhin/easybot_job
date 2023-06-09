package ru.perm.v.easybot.rest;

import org.junit.jupiter.api.Test;
import ru.perm.v.easybot.dto.ProductDTO;
import ru.perm.v.easybot.entity.ProductEntity;
import ru.perm.v.easybot.service.ProductService;

import java.util.ArrayList;
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
        Long GROUP_ID = 100L;
        List<ProductEntity> products = new ArrayList<>();
        products.add(new ProductEntity(ID_1, "NAME_" + ID_1, GROUP_ID));
        products.add(new ProductEntity(ID_2, "NAME_" + ID_2, GROUP_ID));
        try {
            when(service.getAll()).thenReturn(products);
        } catch (Exception e) {
            fail();
        }
        List<ProductDTO> savedProducts = controller.getAll();

        assertEquals(2, savedProducts.size());

        assertEquals(ID_1, savedProducts.get(0).getId());
        assertEquals("NAME_" + ID_1, savedProducts.get(0).getName());
        assertEquals(GROUP_ID, savedProducts.get(0).getGroupProductId());

        assertEquals(ID_2, savedProducts.get(1).getId());
        assertEquals("NAME_" + ID_2, savedProducts.get(1).getName());
        assertEquals(GROUP_ID, savedProducts.get(1).getGroupProductId());
    }

    @Test
    void getById() throws Exception {
        ProductService service = mock(ProductService.class);
        ProductRestController controller = new ProductRestController(service);
        Long ID_1 = 1L;
        String NAME = "NAME_1";
        Long GROUP_ID = 100L;
        try {
            when(service.getById(ID_1)).thenReturn(new ProductEntity(ID_1, NAME, GROUP_ID));
        } catch (Exception e) {
            fail();
        }
        ProductDTO product = controller.getById(ID_1);

        assertEquals(ID_1, product.getId());
        assertEquals(NAME, product.getName());
        assertEquals(GROUP_ID, product.getGroupProductId());
    }

    @Test
    void getByIdNotFound() throws Exception {
        ProductService service = mock(ProductService.class);
        ProductRestController controller = new ProductRestController(service);
        Long ID_1 = 1L;
        try {
            when(service.getById(ID_1)).thenReturn(null);
        } catch (Exception e) {
            fail();
        }
        assertThrows(Exception.class, () -> controller.getById(ID_1));
    }

    @Test
    void save() {
        ProductService service = mock(ProductService.class);
        ProductRestController controller = new ProductRestController(service);
        Long ID = 1L;
        String NAME = "NAME_1";
        Long GROUP_ID = 100L;
        ProductDTO dto = new ProductDTO(ID, NAME, GROUP_ID);
        ProductEntity savedProduct = new ProductEntity(ID, NAME, GROUP_ID);
        ProductDTO returnedDTO = null;

        try {
            when(service.update(ID, NAME, GROUP_ID)).thenReturn(savedProduct);
            returnedDTO = controller.save(dto);
        } catch (Exception e) {
            fail();
        }

        assertEquals(new ProductDTO(ID, NAME, GROUP_ID), returnedDTO);
    }

    @Test
    void create() {
        ProductService service = mock(ProductService.class);
        ProductRestController controller = new ProductRestController(service);
        Long ID = 1L;
        String NAME = "NAME_1";
        Long GROUP_ID = 100L;
        ProductDTO dto = new ProductDTO(ID, NAME, GROUP_ID);
        ProductEntity savedProduct = new ProductEntity(ID, NAME, GROUP_ID);
        ProductDTO returnedDTO = null;
        try {
            when(service.create(NAME, GROUP_ID)).thenReturn(savedProduct);
            returnedDTO = controller.create(dto);
        } catch (Exception e) {
            fail();
        }
        assertEquals(new ProductDTO(ID, NAME, GROUP_ID), returnedDTO);
    }

    @Test
    void getByGroupId() {
        ProductService service = mock(ProductService.class);
        ProductRestController controller = new ProductRestController(service);
        Long ID_1 = 1L;
        Long ID_2 = 2L;
        Long GROUP_ID = 100L;
        List<ProductEntity> products = new ArrayList<>();
        products.add(new ProductEntity(ID_1, "NAME_" + ID_1, GROUP_ID));
        products.add(new ProductEntity(ID_2, "NAME_" + ID_2, GROUP_ID));
        try {
            when(service.getByIdGroupProduct(GROUP_ID)).thenReturn(products);
        } catch (Exception e) {
            fail();
        }
        List<ProductDTO> returnedProducts = null;
        try {
            returnedProducts = controller.getByGroupId(GROUP_ID);
        } catch (Exception e) {
            fail();
        }

        assertEquals(2, returnedProducts.size());

        assertEquals(ID_1, returnedProducts.get(0).getId());
        assertEquals("NAME_" + ID_1, returnedProducts.get(0).getName());
        assertEquals(GROUP_ID, returnedProducts.get(0).getGroupProductId());

        assertEquals(ID_2, returnedProducts.get(1).getId());
        assertEquals("NAME_" + ID_2, returnedProducts.get(1).getName());
        assertEquals(GROUP_ID, returnedProducts.get(1).getGroupProductId());
    }
}