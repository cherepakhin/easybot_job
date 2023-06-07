package ru.perm.v.easybot.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.easybot.dto.ProductDTO;
import ru.perm.v.easybot.entity.ProductEntity;
import ru.perm.v.easybot.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO 1. Добавление товара
 * TODO 2. Редактирование товара
 * TODO 3. Просмотр всех существующих товаров по типу
 * TODO 4. Просмотр товара по идентификатору
 * TODO 5. Добавить cache
 */
@RestController
@RequestMapping("/product")
@Slf4j
@Validated
@AllArgsConstructor
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id) throws Exception {
        ProductEntity entity = productService.getById(id);
        if(entity == null) {
            throw new Exception(String.format("Product not found id=%s",id));
        }
        return new ProductDTO(entity.getId(), entity.getName(), entity.getGroupProductId());
    }

    //TODO: Add cache
    @GetMapping("/")
    public List<ProductDTO> getAll() throws Exception {
        List<ProductDTO> products = productService.getAll()
                .stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getGroupProductId()))
                .collect(Collectors.toList());
        return products;
    }

}
