package ru.perm.v.easybot.service;

import ru.perm.v.easybot.entity.GroupProductEntity;

import java.util.List;

public interface GroupProductService {
    List<GroupProductEntity> getAll();
    GroupProductEntity getById(Long id) throws Exception;
    GroupProductEntity save(Long id, String name, Long parentId, Boolean isLast) throws Exception;
    GroupProductEntity save(GroupProductEntity groupProduct) throws Exception;
    GroupProductEntity create(String name, Long parentId, Boolean isLast) throws Exception;
    void delete(Long parentId) throws Exception;
    List<GroupProductEntity> findByParentId(Long id);

    /**
     * find all LAST group by ID group sorted by id. Need for link Product-GroupProduct.
     * Product can link ONLY to LAST group. LAST group - don't have subgroup.
     * @param id group id
     * @return list GroupProduct
     */
    List<GroupProductEntity> findAllLastGroupByAnyGroupId(Long id);
    Long getMaxId();
}
