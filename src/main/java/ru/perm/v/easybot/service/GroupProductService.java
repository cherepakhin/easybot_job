package ru.perm.v.easybot.service;

import ru.perm.v.easybot.entity.GroupProductEntity;

import java.util.List;

public interface GroupProductService {
    List<GroupProductEntity> getAll();
    GroupProductEntity getById(Long id) throws Exception;
    GroupProductEntity save(Long id, String name, Long parentId) throws Exception;
    GroupProductEntity save(GroupProductEntity groupProduct) throws Exception;
    GroupProductEntity create(String name, Long parentId);
}
