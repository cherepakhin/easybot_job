package ru.perm.v.easybot.dto;

import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
public class ProductDTO {
    private Long id = -1L;
    private String name = "";
    private Long groupProductId = -1L;

    // EMPTY CONSTRUCTOR NEED FOR JACKSON!!!
    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Long groupProductId) {
        this.id = id;
        this.name = name;
        this.groupProductId = groupProductId;
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

    public Long getGroupProductId() {
        return groupProductId;
    }

    public void setGroupProductId(Long groupProductId) {
        this.groupProductId = groupProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
                Objects.equals(groupProductId, that.groupProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, groupProductId);
    }
}
