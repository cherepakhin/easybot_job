package ru.perm.v.easybot.service;

import ru.perm.v.easybot.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity getById(Long id) throws Exception;
    List<ProductEntity> getAll() throws Exception;
//    Long getMaxId();
    ProductEntity create(String name, Long groupId) throws Exception;

}
