package ru.perm.v.easybot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.perm.v.easybot.service.GroupProductService;
import ru.perm.v.easybot.service.ProductService;
import ru.perm.v.easybot.service.ResetDBService;

@Service
@Slf4j
public class ResetDBServiceImpl implements ResetDBService {

    private final ProductService productService;
    private final GroupProductService groupProductService;

    public ResetDBServiceImpl(@Autowired GroupProductService groupProductService,
                              @Autowired ProductService productService) {
        this.groupProductService = groupProductService;
        this.productService = productService;
    }

    public void reset() throws Exception {
        log.info("Reset database");
        productService.reset();
//        groupProductService.reset();
    }
}
