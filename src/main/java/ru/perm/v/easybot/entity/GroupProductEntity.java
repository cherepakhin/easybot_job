package ru.perm.v.easybot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "group_product")
public class GroupProductEntity {
    @Id
    @Column(name = "id")
    private Long id = -1L;
    @Column(name = "name", nullable = false)
    private String name = "";
    @Column(name = "parent_id", nullable = false)
    private Long parentId = -1L;
    //TODO: add tree index String "01.20.30..." or Long 010230...

    public GroupProductEntity() {
    }

    public GroupProductEntity(Long id, String name, Boolean isLast, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupProductEntity that = (GroupProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId);
    }
}
