package com.epam.ld.module2.testing.template;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Template.
 */
public class Template {
    private final String rawText;

    public Template(String rawText) {
        this.rawText = rawText;
    }

    public String getRawText() {
        return rawText;
    }
}
