package ru.perm.v.easybot.rest.validators;

import org.junit.jupiter.api.Test;
import ru.perm.v.easybot.dto.ProductDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorProductDtoTest {

    @Test
    void validate() {
        ValidatorProductDto validator = new ValidatorProductDto();
        ProductDTO dto = new ProductDTO();
        dto.setName("");
        List<String> errors = validator.validate(dto);
        assertTrue(errors.size() > 0);
        assertEquals("field: name, error: не должно быть пустым\n", errors.get(0));
    }
}