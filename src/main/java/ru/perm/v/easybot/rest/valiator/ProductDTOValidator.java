package ru.perm.v.easybot.rest.valiator;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.perm.v.easybot.dto.ProductDTO;

@Service
public class ProductDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ProductDTO dto = (ProductDTO) obj;
        if (dto.getId() == null || dto.getId().equals(31000L)) {
            errors.rejectValue("id", "id is null");
        }
        if (dto.getGroupProductId() == null) {
            errors.rejectValue("groupProductId", "groupProductId is null");
        }
    }
}
