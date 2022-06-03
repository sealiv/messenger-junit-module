package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.utils.TagUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Template engine.
 */
public class TemplateEngine {

    private final TagUtils tagUtils = new TagUtils();

    /**
     * Generate message string.
     *
     * @param template the template
     * @return the string
     */
    public String generateMessage(Template template) {
        Set<String> tags = findRawTags(template.getRawText());
        String tagValues = tagUtils.getTagValues(tags);
        Map<String, String> params = tagUtils.getParams(tags, tagValues);
        return fillTags(template, params);
    }

    /**
     * Changing template tags to their values.
     *
     * @param template the template
     * @param params the map of tag values
     * @return the string
     */
    String fillTags(Template template, Map<String, String> params) {
        String result = template.getRawText();
        for (String s: params.keySet()) {
            result = result.replaceAll("#\\{" + s + "}", params.get(s));
        }
        return result;
    }

    /**
     * Searching tags in template.
     *
     * @param text the text from template
     * @return the set of string
     */
    Set<String> findRawTags(String text) {
        Set<String> tags = new TreeSet<>();
        Pattern pattern = Pattern.compile("(#\\{)(.+?)(})");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            tags.add(matcher.group(2));
        }
        return tags;
    }
}
