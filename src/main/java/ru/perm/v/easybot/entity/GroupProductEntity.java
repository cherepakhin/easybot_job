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
    @Column(name = "id", nullable = false)
    private Long id = -1L;
    @Column(name = "id", nullable = false)
    private String name = "";
    @Column(name = "is_last", nullable = false)
    private Boolean isLast = false;

    public GroupProductEntity() {
    }

    public GroupProductEntity(Long id, String name, Boolean isLast) {
        this.id = id;
        this.name = name;
        this.isLast = isLast;
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

    public Boolean getIsLast() {
        return isLast;
    }

    public void setIsLast(Boolean last) {
        isLast = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupProductEntity that = (GroupProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(isLast, that.isLast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isLast);
    }
}
