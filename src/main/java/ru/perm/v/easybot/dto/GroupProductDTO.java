package ru.perm.v.easybot.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class GroupProductDTO {
    @NotNull
    private Long id = -1L;
    @NotNull
    private String name = "";
    @NotNull
    private Long parentId = -1L;
    // if true, then not childs. For optimize sql request and Product must link to last group.
    @NotNull
    private Boolean isLast = true;

    // EMPTY CONSTRUCTOR NEED FOR JACKSON!!!
    public GroupProductDTO() {
    }

    public GroupProductDTO(Long id, String name, Long parentId, Boolean isLast) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupProductDTO that = (GroupProductDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(parentId, that.parentId) && Objects.equals(isLast, that.isLast);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, isLast);
    }
}
