package ru.perm.v.easybot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.perm.v.easybot.entity.ProductEntity;
import ru.perm.v.easybot.repository.ProductRepository;
import ru.perm.v.easybot.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    public ProductServiceImpl(@Autowired ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductEntity getById(Long id) throws Exception {
        return repository.getById(id);
    }

    @Override
    public List<ProductEntity> getAll() throws Exception {
        return repository.findAll(Sort.by("name"));
    }

    @Override
    public Long getMaxId() {
        return repository.getMaxId();
    }

    @Override
    public ProductEntity create(String name, Long groupId) throws Exception {
        Long id = getMaxId() + 1;
        ProductEntity product = new ProductEntity(id, name, groupId);
        ProductEntity saved = repository.save(product);
        return saved;
    }
}