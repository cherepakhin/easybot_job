package ru.perm.v.easybot.rest.validators;

import ru.perm.v.easybot.dto.ProductDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidatorProductDto {
    public List<String> validate(ProductDTO dto) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();
        Set<ConstraintViolation<ProductDTO>> validates = validator.validate(dto);
        List<String> ret = new ArrayList<>();
        if (validates.size() > 0) {
//            StringBuilder err = new StringBuilder(String.format("%s. Errors: ", dto.toString()));
            List<ConstraintViolation<ProductDTO>> errors = validates.stream().collect(Collectors.toList());
            for (ConstraintViolation<ProductDTO> validateErr : errors) {
                ret.add(String.format("field: %s, error: %s\n", validateErr.getPropertyPath(), validateErr.getMessage()));
            }
        }
        return ret;
    }
}
