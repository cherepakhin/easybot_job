package ru.perm.v.easybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc // Need for OpenApiDoc
@EnableCaching
public class EasybotShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasybotShopApplication.class, args);
    }

}
