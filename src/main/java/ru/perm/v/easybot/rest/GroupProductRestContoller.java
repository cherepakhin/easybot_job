package ru.perm.v.easybot.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.easybot.dto.GroupProductDTO;
import ru.perm.v.easybot.entity.GroupProductEntity;
import ru.perm.v.easybot.service.GroupProductService;

import java.util.Arrays;
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

    @GetMapping("/")
    public List<GroupProductDTO> getAll() {
        List<GroupProductEntity> entities = groupProductService.getAll();
        return entities.stream().map(entity -> new GroupProductDTO(entity.getId(), entity.getName(), entity.getIsLast(), entity.getParentId())).collect(Collectors.toList());
//        (new GroupProductDTO(-1L, "-",false, 0L)
//        return Arrays.asList(new GroupProductDTO(-1L, "-",false, 0L));
    }

    @GetMapping("/echo")
    public String echo() {
        return "Ok";
    }

}
