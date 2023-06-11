package ru.perm.v.easybot.service;

import ru.perm.v.easybot.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity getById(Long id) throws Exception;

    List<ProductEntity> getAll() throws Exception;

    ProductEntity create(String name, Long groupId) throws Exception;

    ProductEntity update(Long id, String name, Long groupId) throws Exception;

    ProductEntity update(ProductEntity product) throws Exception;

    void delete(Long id) throws Exception;

    List<ProductEntity> getByIdGroupProduct(Long id);

    void reset();
}
