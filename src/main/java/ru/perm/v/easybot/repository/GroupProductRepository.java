package ru.perm.v.easybot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.perm.v.easybot.entity.GroupProductEntity;

import java.util.List;

@Repository
@Transactional
public interface GroupProductRepository extends JpaRepository<GroupProductEntity, Long> {
    List<GroupProductEntity> findAllByOrderByIdAsc();

    @Query(value = "SELECT max(id) FROM GroupProductEntity")
    Long getMaxId();

    List<GroupProductEntity> findByParentIdOrderByParentIdAsc(Long id);

    List<GroupProductEntity> findByParentIdOrderByParentIdAscIdAsc(Long id);
}
