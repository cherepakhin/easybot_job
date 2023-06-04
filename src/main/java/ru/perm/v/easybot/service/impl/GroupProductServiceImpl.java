package ru.perm.v.easybot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.repository.GroupProductRepository;
import ru.perm.v.easybot.service.GroupProductService;

import java.util.List;

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
        if (groupProduct.getId().compareTo(-1L) == 0) {
            throw new Exception("Not found");
        }
        return groupProduct;
    }
}
