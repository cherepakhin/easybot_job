package ru.perm.v.easybot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.repository.GroupProductRepository;
import ru.perm.v.easybot.service.GroupProductService;

import java.util.List;

import static ru.perm.v.easybot.entity.EntityConsts.GROUP_PRODUCT_ID_NOT_FOUND;

@Service
public class GroupProductServiceImpl implements GroupProductService {

    @Autowired
    private GroupProductRepository repository;

    public GroupProductServiceImpl(@Autowired GroupProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GroupProductEntity> getAll() {
        return repository.findAllByOrderByIdAsc();
    }

    @Override
    public GroupProductEntity getById(Long id) throws Exception {
        GroupProductEntity groupProduct = repository.getById(id);
        if (groupProduct.getId().compareTo(GROUP_PRODUCT_ID_NOT_FOUND) == 0) {
            throw new Exception("Not found");
        }
        return groupProduct;
    }

    @Override
    public GroupProductEntity save(Long id, String name, Long parentId) throws Exception {
        try {
            getById(parentId);
            GroupProductEntity groupProduct = new GroupProductEntity();
            groupProduct.setId(id);
            groupProduct.setName(name);
            groupProduct.setParentId(parentId);
            return save(groupProduct);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public GroupProductEntity save(GroupProductEntity groupProduct) throws Exception {
        try {
            return repository.save(groupProduct);
        } catch (Exception e) {
            throw e;
        }
    }
}