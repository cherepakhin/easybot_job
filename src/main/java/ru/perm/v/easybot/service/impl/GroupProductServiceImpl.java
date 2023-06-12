package ru.perm.v.easybot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.repository.GroupProductRepository;
import ru.perm.v.easybot.service.GroupProductService;
import ru.perm.v.easybot.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static ru.perm.v.easybot.entity.EntityConsts.GROUP_PRODUCT_ID_NOT_FOUND;

@Service
public class GroupProductServiceImpl implements GroupProductService {

    private final ProductService productService;
    private final GroupProductRepository repository;

    public GroupProductServiceImpl(@Autowired GroupProductRepository repository, @Autowired ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    public GroupProductEntity create(String name, Long parentId, Boolean isLast) throws Exception {
        GroupProductEntity parent = getById(parentId);
        GroupProductEntity entity = new GroupProductEntity();
        entity.setId(getMaxId() + 1);
        entity.setName(name);
        entity.setParentId(parent.getId());
        entity.setIsLast(isLast);
        return repository.save(entity);
    }

    @Override
    public GroupProductEntity save(Long id, String name, Long parentId, Boolean isLast) throws Exception {
        if (id == null) {
            return create(name, parentId, isLast);
        }
        try {
            GroupProductEntity parent = getById(parentId);
            getById(parentId);
            GroupProductEntity groupProduct = new GroupProductEntity();
            groupProduct.setId(id);
            groupProduct.setName(name);
            groupProduct.setParentId(parent.getId());
            groupProduct.setIsLast(isLast);
            return save(groupProduct);
        } catch (Exception e) {
            throw new Exception(
                    String.format("Error save GroupProductEntity id=%s, name=%s, paentId=%s", id, name, parentId));
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

    @Override
    public List<GroupProductEntity> getAll() {
        return repository.findAllByOrderByIdAsc();
    }

    @Override
    public GroupProductEntity getById(Long id) throws Exception {
        GroupProductEntity groupProduct = repository.getById(id);
        if (groupProduct.getId().compareTo(GROUP_PRODUCT_ID_NOT_FOUND) == 0) {
            throw new Exception(String.format("Not found GroupProductEntity id = ", id));
        }
        return groupProduct;
    }


    @Override
    public void delete(Long id) throws Exception {
        GroupProductEntity groupProduct = getById(id);
        if (isProductsInGroup(groupProduct)) {
            throw new Exception(
                    String.format("Can't delete group id=%s, group=%s. There are products in the group or subgroup.", id, groupProduct.getName()));
        }
        if (findByParentId(id).size() > 0) {
            throw new Exception(
                    String.format("Can't delete group id=%s, group=%s. There are subgroups in the group.", id, groupProduct.getName()));
        }
        repository.delete(groupProduct);
    }

    @Override
    public List<GroupProductEntity> findByParentId(Long id) {
        return repository.findByParentIdOrderByParentIdAsc(id);
    }

    @Override
    public List<GroupProductEntity> findAllLastGroupByAnyGroupId(Long id) {
        List<GroupProductEntity> ret = new ArrayList<>();
        List<GroupProductEntity> groups = repository.findByParentIdOrderByParentIdAsc(id);
        for (GroupProductEntity g : groups) {
            List<GroupProductEntity> childs = findByParentId(g.getId());
            if (childs.size() == 0) {
                ret.add(g);
            }
        }
        ret.sort((g1, g2) -> g1.getId().compareTo(g2.getId()));
        return ret;
    }

    @Override
    public Long getMaxId() {
        return repository.getMaxId();
    }

    protected boolean isProductsInGroup(GroupProductEntity groupProduct) {
        return productService.getByIdGroupProduct(groupProduct.getId()).size() > 0;
    }

    @Override
    public void reset() {
        repository.reset();
    }

}
