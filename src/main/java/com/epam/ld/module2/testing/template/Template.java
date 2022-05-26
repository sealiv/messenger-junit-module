package com.epam.ld.module2.testing.template;

/**
 * The type Template.
 */
public class Template {

    private final String text;

    public Template(String text) {
        this.text = text;
    }

    public String setTag(String key, String value) {
        return text.replaceAll("#\\{" + key + "}", value);
    }
}
