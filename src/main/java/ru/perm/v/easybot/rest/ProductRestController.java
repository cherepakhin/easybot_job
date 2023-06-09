package ru.perm.v.easybot.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import ru.perm.v.easybot.dto.ProductDTO;
import ru.perm.v.easybot.entity.ProductEntity;
import ru.perm.v.easybot.rest.excpt.BadRequestException;
import ru.perm.v.easybot.rest.excpt.Err500Exception;
import ru.perm.v.easybot.rest.validators.ValidatorProductDto;
import ru.perm.v.easybot.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@Slf4j
@Api(tags = "product-api")
public class ProductRestController {
    private ProductService productService;
    private ValidatorProductDto validator = new ValidatorProductDto();

    public ProductRestController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/reset")
    @ApiOperation("Reset products")
    public void reset() {
        productService.reset();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get ProductDTO by id")
    @Cacheable(value = "product", key = "#id")
    public ProductDTO getById(@PathVariable Long id) throws Exception {
        ProductEntity entity = productService.getById(id);
        if (entity == null) {
            String err = String.format("Product not found id=%s", id);
            log.error(err);
            throw new BadRequestException(err);
        }
        return new ProductDTO(entity.getId(), entity.getName(), entity.getGroupProductId());
    }

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
    @CacheEvict(value = "product", key = "#productDTO.id")
    public ProductDTO save(@RequestBody ProductDTO productDTO) throws Exception {
        List<String> errors = validator.validate(productDTO);
        if (errors.size() > 0) {
            throw new BadRequestException(errors.stream().collect(Collectors.joining()));
        }
        ProductEntity product =
                productService.update(productDTO.getId(), productDTO.getName(), productDTO.getGroupProductId());
        // используется именно такой конструктор (не new ProductDTO(productEntity),
        // чтобы отвязаться от всяких JPA зависимостей
        return new ProductDTO(product.getId(), product.getName(), product.getGroupProductId());
    }

    @PutMapping("/")
    @ApiOperation("Create ProductDTO")
    public ProductDTO create(@RequestBody ProductDTO productDTO) throws Exception {
        log.info("Create %s", productDTO.toString());
        List<String> errors = validator.validate(productDTO);
        if (errors.size() > 0) {
            String errorMes = String.format("Product not created %s, Errors: %s", productDTO, errors.stream().collect(Collectors.joining()));
            throw new BadRequestException(errorMes);
        }
        ProductEntity entity = productService.create(productDTO.getName(), productDTO.getGroupProductId());
        if (entity == null) {
            String errEntityMes = String.format("Product not created dto=%s", productDTO);
            log.error(errEntityMes);
            throw new BadRequestException(errEntityMes);
        }
        return new ProductDTO(entity.getId(), entity.getName(), entity.getGroupProductId());
    }

    @GetMapping("/groupId/{groupId}")
    @ApiOperation("Get ProductDTO by GroupId")
    public List<ProductDTO> getByGroupId(@PathVariable Long groupId) {
        log.info("Get all products by groupId=%s", groupId);
        return productService.getByIdGroupProduct(groupId).stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getGroupProductId()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete ProductDTO by id")
    @CacheEvict(value = "product", key = "#id")
    public void delete(@PathVariable("id") Long id) {
        log.info("Delete product %s", id);
        try {
            productService.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Err500Exception(e.getMessage());
        }
    }
}
