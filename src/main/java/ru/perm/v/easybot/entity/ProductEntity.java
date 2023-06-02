package ru.perm.v.easybot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id = -1L;
    @Column(name = "name", nullable = false)
    private String name = "";

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity productDB = (ProductEntity) o;
        return Objects.equals(id, productDB.id) && Objects.equals(name, productDB.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
