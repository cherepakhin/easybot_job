package ru.perm.v.easybot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.repository.GroupProductRepository;
import ru.perm.v.easybot.service.GroupProductService;

import java.util.List;

@Service
public class  GroupProductServiceImpl implements GroupProductService {

    @Autowired
    private GroupProductRepository repository;

    @Override
    public List<GroupProductEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public GroupProductEntity getById(Long id) {
        return repository.getById(id);
    }
}
