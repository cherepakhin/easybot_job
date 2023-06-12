package ru.perm.v.easybot.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@Api(tags = "group-product-api")
public class GroupProductRestContoller {
    @Autowired
    private GroupProductService groupProductService;

    /**
     * Rest is work? Need for integration test.
     *
     * @return "Ok"
     */
    @GetMapping("/echo")
    @ApiOperation("Simple echo for test")
    public String echo() {
        return "Ok";
    }

    @GetMapping("/evict/{id}")
    @ApiOperation("Clear cache by id. ONLY FOR DEMO!")
    @CacheEvict(value = "group_product", key = "#id")
    public String evict(@PathVariable("id") Long id) {
        return "Ok";
    }

    @GetMapping("/")
    @ApiOperation("Get all GroupProductDTO ")
    public List<GroupProductDTO> getAll() {
        List<GroupProductEntity> entities = groupProductService.getAll();
        return entities.stream().map(entity ->
                        new GroupProductDTO(entity.getId(), entity.getName(), entity.getParentId(), entity.getIsLast()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation("Get GroupProductDTO by id")
    @Cacheable(value = "group_product")
    public GroupProductDTO getById(@PathVariable("id") Long id) {
        log.info(String.format("GET GroupProductDTO id=%s", id));
        GroupProductEntity entity = null;
        try {
            entity = groupProductService.getById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (entity == null) {
            throw new ResourceNotFoundException(String.format("Product group with %s not found.", id));
        }
        return new GroupProductDTO(entity.getId(), entity.getName(), entity.getParentId(), entity.getIsLast());
    }

    @PostMapping("/")
    @ApiOperation("Create GroupProductDTO")
    public GroupProductDTO create(String name, Long parentId, Boolean isLast) {
        log.info(String.format("Create GroupProductDTO name=%s, parentId=%s", name, parentId));
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

    @PostMapping("/{id}")
    @ApiOperation("Upadte GroupProductDTO")
    @CacheEvict(value = "group_product", key = "#id")
    public GroupProductDTO update(@PathVariable("id") Long id, String name, Long parentId, Boolean isLast) {
        log.info(String.format("Update GroupProductDTO id=%s, name=%s, parentId=%s", id, name, parentId));
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

    @DeleteMapping("/{id}")
    @ApiOperation("Delete GroupProductDTO")
    @CacheEvict(value = "group_product", key = "#id")
    public void delete(@PathVariable("id") Long id) {
        log.info("Delete group product %s", id);
        try {
            groupProductService.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Err500Exception(e.getMessage());
        }
    }

    @GetMapping("/reset")
    @ApiOperation("Reset GrouProduct")
    public void reset() {
        groupProductService.reset();
    }
}
