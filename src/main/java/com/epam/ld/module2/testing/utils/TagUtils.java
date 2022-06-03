package com.epam.ld.module2.testing.utils;

import com.epam.ld.module2.testing.exception.LeftRawTagsException;
import java.util.*;

/**
 * The utils for work with tags.
 */
public class TagUtils {

    /**
     * Getting the tag values as console input.
     *
     * @param tags the Set of tags from template
     * @return the String with tag values.
     */
    public String getTagValues(Set<String> tags) {
        Scanner scanner = new Scanner(System.in);
        String inviteToInput = getInvite(tags);
        System.out.print(inviteToInput);
        return scanner.nextLine();
    }

    private String getInvite(Set<String> tags) {
        return "Input string with parameters. Use 'space symbol' between values." + System.lineSeparator()
                + String.join("  ", tags) + " --> ";
    }

    /**
     * Make map from Set of tag and String of values.
     * @param tags the Set of tags from template
     * @param tagValues the String with tag values.
     * @return the Map with tag values.
     */
    public Map<String, String> getParams(Set<String> tags, String tagValues) {
        Map<String, String> params = new HashMap<>();
        String[] values = tagValues.split(" ");
        if (values.length < tags.size()) {
            throw new LeftRawTagsException("Template consist more raw tags than passed values.");
        }
        int step = 0;
        for (String s : tags) {
            params.put(s, values[step++]);
        }
        return params;
    }
}
