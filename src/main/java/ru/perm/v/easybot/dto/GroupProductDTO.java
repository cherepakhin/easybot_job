package ru.perm.v.easybot.dto;

import java.util.Objects;

public class GroupProductDTO {
    private Long id = -1L;
    private String name = "";
    private Boolean isLast = false;
    private Long parentId = -1L;

    public GroupProductDTO(Long id, String name, Boolean isLast, Long parentId) {
        this.id = id;
        this.name = name;
        this.isLast = isLast;
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

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
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
        GroupProductDTO that = (GroupProductDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(isLast, that.isLast) && Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isLast, parentId);
    }
}
