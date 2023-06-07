package ru.perm.v.easybot.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.perm.v.easybot.dto.GroupProductDTO;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.rest.excpt.Err500Exception;
import ru.perm.v.easybot.rest.excpt.ResourceNotFoundException;
import ru.perm.v.easybot.service.GroupProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/group_product")
@Slf4j
@Validated
@AllArgsConstructor
public class GroupProductRestContoller {
    @Autowired
    private GroupProductService groupProductService;

    //TODO: Add cache
    @GetMapping("/")
    public List<GroupProductDTO> getAll() {
        List<GroupProductEntity> entities = groupProductService.getAll();
        return entities.stream().map(entity ->
                        new GroupProductDTO(entity.getId(), entity.getName(), entity.getParentId(), entity.getIsLast()))
                .collect(Collectors.toList());
    }

    //TODO: Add cache
    @GetMapping("/{id}")
    public GroupProductDTO getById(@PathVariable("id") Long id) {
        GroupProductEntity entity = null;
        try {
            entity = groupProductService.getById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new GroupProductDTO(entity.getId(), entity.getName(), entity.getParentId(), entity.getIsLast());
    }

    //TODO: Add clear cache
    @PostMapping("/")
    public GroupProductDTO create(String name, Long parentId, Boolean isLast) {
        GroupProductEntity entity = null;
        try {
            entity = groupProductService.create(name, parentId, isLast);
        } catch (Exception e) {
            String errMessage = String.format("Parent group(id=%s) is LAST. Can`t add to LAST group", parentId);
            log.error(errMessage);
            throw new ResourceNotFoundException(errMessage);
        }
        return new GroupProductDTO(entity.getId(), entity.getName(), entity.getParentId(), entity.getIsLast());
    }

    //TODO: Add clear cache
    @PostMapping("/{id}")
    public GroupProductDTO update(@PathVariable("id") Long id, String name, Long parentId, Boolean isLast) {
        GroupProductEntity entity = null;
        try {
            groupProductService.getById(id);
        } catch (Exception e) {
            String errMessage = String.format("Group id=%s no found", id);
            log.error(errMessage);
            throw new ResourceNotFoundException(errMessage);
        }
        try {
            groupProductService.getById(parentId);
        } catch (Exception e) {
            String errMessage = String.format("Parent group id=%s no found", parentId);
            log.error(errMessage);
            throw new ResourceNotFoundException(errMessage);
        }
        try {
            entity = groupProductService.save(id, name, parentId, isLast);
        } catch (Exception e) {
            String errMessage = String.format("Parent group(id=%s) is LAST. Can`t add to LAST group", parentId);
            log.error(errMessage);
            throw new ResourceNotFoundException(errMessage);
        }
        return new GroupProductDTO(entity.getId(), entity.getName(), entity.getParentId(), entity.getIsLast());
    }

    //TODO: Add clear cache
    @DeleteMapping("/")
    public void delete(Long id) {
        try {
            groupProductService.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Err500Exception(e.getMessage());
        }
    }

    //TODO: update
    //TODO: Add clear cache

    /**
     * Rest is work? Need for integration test.
     * @return "Ok"
     */
    @GetMapping("/echo")
    public String echo() {
        return "Ok";
    }

}
