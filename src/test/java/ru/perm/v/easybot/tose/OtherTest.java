package ru.perm.v.easybot.tose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtherTest {
    @Test
    void stringFormat() {
        Long v = 1L;
        assertEquals("Long = 1", String.format("Long = %s", v));
    }
}
