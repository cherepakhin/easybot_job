package ru.perm.v.easybot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.perm.v.easybot.entity.ProductEntity;

import javax.persistence.Table;
import java.util.List;

@Repository
@Transactional
@Table(name = "product")
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByOrderByIdAsc();

    @Query(value = "SELECT max(id) FROM ProductEntity")
    Long getMaxId();

    List<ProductEntity> findByGroupProductIdOrderByIdAsc(Long groupId);

    /**
     * delete all products"
     */
    @Query(value = "delete FROM ProductEntity")
    @Modifying
    void reset();
}
