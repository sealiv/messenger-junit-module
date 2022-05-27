package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exception.LeftRawTagsException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        template.addTags(client.getAddresses());
        String resultText = template.getText();
        Optional<String> rawTag = findFirstRawTag(resultText);
        if (rawTag.isPresent()) {
            throw new LeftRawTagsException("Template consist at least follow raw tag: #{" + rawTag.get() + "}.");
        }
        return resultText;
    }

    private Optional<String> findFirstRawTag(String text) {
        Pattern pattern = Pattern.compile("(#\\{)(.+?)(})");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Optional.of(matcher.group(2));
        }
        return Optional.empty();
    }
}
