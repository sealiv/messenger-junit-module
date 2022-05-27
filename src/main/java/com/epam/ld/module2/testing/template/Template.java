package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Template.
 */
public class Template {

    private String text;
    private final Set<String> tags = new TreeSet<>();
    private final Map<String, String> params = new HashMap<>();

    public Template(String text) {
        this.text = text;
    }

    public void setTag(String key, String value) {
        this.text = text.replaceAll("#\\{" + key + "}", value);
    }

    public void addTags(String email) {
        findTags();
        getTagValueFromConsole();
        params.forEach(this::setTag);
        setTag("subject", email);
    }

    void getTagValueFromConsole() {
        Scanner scanner = new Scanner(System.in);
        for (String s : tags) {
            if (s.equals("subject")) {
                continue;
            }
            System.out.printf("input value from '%s': ", s);
            String value = scanner.nextLine();
            params.put(s, value);
        }
    }

    private void findTags() {
        Pattern pattern = Pattern.compile("(#\\{)(.+?)(})");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            tags.add(matcher.group(2));
        }
    }

    public String getText() {
        return text;
    }
}
