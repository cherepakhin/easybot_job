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
        return repository.findById(id).orElseThrow(
                () -> new Exception(String.format("Not found product with id=%s", id)));
    }

    @Override
    public List<ProductEntity> getAll() throws Exception {
        return repository.findAll(Sort.by("name"));
    }

    @Override
    public ProductEntity create(String name, Long groupId) throws Exception {
        Long id = repository.getMaxId() + 1;
        ProductEntity product = new ProductEntity(id, name, groupId);
        return repository.save(product);
    }

    @Override
    public ProductEntity update(Long id, String name, Long groupId) throws Exception {
        ProductEntity product = getById(id);
        product.setName(name);
        product.setGroupProductId(groupId);
        return repository.save(product);
    }

    @Override
    public ProductEntity update(ProductEntity product) throws Exception {
        return update(product.getId(), product.getName(), product.getGroupProductId());
    }

    @Override
    public void delete(Long id) throws Exception {
        getById(id); // check for exist
        repository.deleteById(id);
    }

    /**
     * Get products by ID group.
     * Sort by ID.
     *
     * @param idGroup - ID group product
     * @return list products in group
     */
    @Override
    public List<ProductEntity> getByIdGroupProduct(Long idGroup) {
        return repository.findByGroupProductIdOrderByIdAsc(idGroup);
    }

    @Override
    public void reset() {
        repository.reset();
    }
}
