package com.epam.ld.module2.testing.utils;

import java.util.ArrayList;
import java.util.List;

public class FreeTemplates {

    private static final List<String> BLANKS = new ArrayList<>();
    private static final FileUtils fileUtils = new FileUtils();

    static {
        BLANKS.add(fileUtils.readResource("template_1.txt"));
        BLANKS.add(fileUtils.readResource("template_2.txt"));
        BLANKS.add(fileUtils.readResource("template_3.txt"));
    }

    public static String getBlank(int number) {
        if (number < BLANKS.size()) {
            return BLANKS.get(number);
        }
        return "";
    }
}
