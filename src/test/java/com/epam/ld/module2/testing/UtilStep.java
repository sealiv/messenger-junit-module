package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.Utils;
import io.cucumber.java.en.Given;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilStep {
    @Given("read file {string} and should find fragment {string} in text")
    public void readFileAndShouldFindFragmentInText(String path, String fragment) {
        String templateText = Utils.readResource(path);
        assertTrue(templateText.contains(fragment));
    }
}
