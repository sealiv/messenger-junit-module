package com.epam.ld.module2.testing.utils;

import com.epam.ld.module2.testing.exception.LeftRawTagsException;
import java.util.*;

public class TagUtils {

    public String getTagValues(Set<String> tags) {
        Scanner scanner = new Scanner(System.in);
        String inviteToInput = getInvite(tags);
        System.out.print(inviteToInput);
        return scanner.nextLine();
    }

    private String getInvite(Set<String> tags) {
        return "Insert one string with parameters like: " + String.join(" ", tags) + " --> ";
//                + "\n" + "if parameter consist with more than one word - type it wrapped in ''";
    }

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
