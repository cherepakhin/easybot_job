package ru.perm.v.easybot.rest.validators;

import org.junit.jupiter.api.Test;
import ru.perm.v.easybot.dto.ProductDTO;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorProductDtoTest {

    @Test
    void validate() {
        ValidatorProductDto validator = new ValidatorProductDto();
        ProductDTO dto = new ProductDTO();
        dto.setName("");
        String err = validator.validate(dto);
        assertEquals("ProductDTO{id=-1, name='', groupProductId=-1}. Errors: field: name, error: не должно быть пустым\n", err);
    }
}