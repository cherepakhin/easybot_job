package ru.perm.v.easybot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EasybotShopApplicationTests {

    @Test
    void simple() {
        assertEquals(true, Boolean.TRUE);
    }
}
