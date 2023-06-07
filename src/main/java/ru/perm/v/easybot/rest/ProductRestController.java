package ru.perm.v.easybot.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.perm.v.easybot.dto.ProductDTO;
import ru.perm.v.easybot.entity.ProductEntity;
import ru.perm.v.easybot.rest.valiator.ProductDTOValidator;
import ru.perm.v.easybot.service.ProductService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO 1. Добавление товара
 * TODO 3. Просмотр всех существующих товаров по типу
 * TODO 5. Добавить cache
 */
@RestController
@RequestMapping("/product")
@Slf4j
@Validated
public class ProductRestController {
    private ProductService productService;

    public ProductRestController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id) throws Exception {
        ProductEntity entity = productService.getById(id);
        if (entity == null) {
            throw new Exception(String.format("Product not found id=%s", id));
        }
        return new ProductDTO(entity.getId(), entity.getName(), entity.getGroupProductId());
    }

    //TODO: Add cache
    @GetMapping("/")
    public List<ProductDTO> getAll() throws Exception {
        return productService.getAll()
                .stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getGroupProductId()))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ProductDTO save(@Valid @RequestBody ProductDTO productDTO) throws Exception {
        ProductEntity product =
                productService.update(productDTO.getId(), productDTO.getName(), productDTO.getGroupProductId());
        // используется именно такой конструктор (не new ProductDTO(productEntity),
        // чтобы отвязаться от всяких JPA зависимостей
        return new ProductDTO(product.getId(), product.getName(), product.getGroupProductId());
    }

    //TODO @PutMapping("/")
    @PutMapping("/")
    public ProductDTO create(@Valid ProductDTO productDTO) throws Exception {
        return new ProductDTO(1L, "NAME", 10L);
    }
}
