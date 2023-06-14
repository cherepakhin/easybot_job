package ru.perm.v.easybot.rest;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.easybot.service.ResetDBService;

@RestController
@RequestMapping("/tools/reset")
@Slf4j
@Api(tags = "reset-api")
@ExtendWith(SpringExtension.class)
/**
 * ONLY FOR INTEGRATION TESTS
 */
public class ResetController {

    @Autowired
    private ResetDBService resetDBService;

    private String Ok = "Ok";

    @GetMapping("/")
    public String resetDatabase() throws Exception {
        log.info("RESET");
        resetDBService.reset();
        return Ok;
    }

}
