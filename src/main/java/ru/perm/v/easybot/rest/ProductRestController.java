package ru.perm.v.easybot.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.perm.v.easybot.dto.ProductDTO;
import ru.perm.v.easybot.entity.ProductEntity;
import ru.perm.v.easybot.rest.excpt.BadRequestException;
import ru.perm.v.easybot.service.ProductService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO 3. Просмотр всех существующих товаров по группе
 * TODO 5. Добавить cache
 * TODO Если успею, то добавить spring.Validator
 */
@RestController
@RequestMapping("/product")
@Slf4j
@Api(tags = "product-api")
public class ProductRestController {
    private ProductService productService;
//    private ProductDTOValidator validator = new ProductDTOValidator();

    public ProductRestController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get ProductDTO by id")
    public ProductDTO getById(@PathVariable Long id) throws Exception {
        ProductEntity entity = productService.getById(id);
        if (entity == null) {
            String err = String.format("Product not found id=%s", id);
            log.error(err);
            throw new BadRequestException(err);
        }
        return new ProductDTO(entity.getId(), entity.getName(), entity.getGroupProductId());
    }

    //TODO: Add cache
    @GetMapping("/")
    @ApiOperation("Get all ProductDTO")
    public List<ProductDTO> getAll() throws Exception {
        return productService.getAll()
                .stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getGroupProductId()))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    @ApiOperation("Save ProductDTO")
    public ProductDTO save(@RequestBody ProductDTO productDTO) throws Exception {
        vaildateProductDTO(productDTO);
        ProductEntity product =
                productService.update(productDTO.getId(), productDTO.getName(), productDTO.getGroupProductId());
        // используется именно такой конструктор (не new ProductDTO(productEntity),
        // чтобы отвязаться от всяких JPA зависимостей
        return new ProductDTO(product.getId(), product.getName(), product.getGroupProductId());
    }

    @PutMapping("/")
    @ApiOperation("Create ProductDTO")
    public ProductDTO create(@RequestBody ProductDTO productDTO) throws Exception {
        vaildateProductDTO(productDTO);
        ProductEntity entity = productService.create(productDTO.getName(), productDTO.getGroupProductId());
        if (entity == null) {
            String err = String.format("Product not created dto=%s", productDTO);
            log.error(err);
            throw new BadRequestException(err);
        }
        return new ProductDTO(entity.getId(), entity.getName(), entity.getGroupProductId());
    }

    @GetMapping("/groupId/{groupId}")
    @ApiOperation("Get ProductDTO by GroupId")
    public List<ProductDTO> getByGroupId(@PathVariable Long groupId) {
        return productService.getByIdGroupProduct(groupId).stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getGroupProductId()))
                .collect(Collectors.toList());
    }

    protected void vaildateProductDTO(ProductDTO productDTO) throws BadRequestException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();
        Set<ConstraintViolation<ProductDTO>> validates = validator.validate(productDTO);
        if (validates.size() > 0) {
            String err = String.format("%s. Errors: ", productDTO.toString());
            List<ConstraintViolation<ProductDTO>> errors = validates.stream().collect(Collectors.toList());
            for (ConstraintViolation<ProductDTO> validateErr : errors) {
                err = err + String.format("field: %s, error: %s\n", validateErr.getPropertyPath(), validateErr.getMessage());
            }
            throw new BadRequestException(err);
        }
    }
}
