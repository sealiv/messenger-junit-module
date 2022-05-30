package com.epam.ld.module2.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTest {

    @Test
    void readFileTest() {
        String templateText = Utils.readResource("./test_template_1.txt");
        assertTrue(templateText.contains("Dear Student"));
    }
}