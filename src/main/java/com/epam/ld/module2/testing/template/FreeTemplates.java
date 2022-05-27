package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Template.
 */
public final class FreeTemplates {

    private static final List<String> BLANKS = new ArrayList<>();

    static {
        BLANKS.add(Utils.readResource("template 01.txt"));
        BLANKS.add(Utils.readResource("template 02.txt"));
        BLANKS.add(Utils.readResource("template 03.txt"));
    }

    public static String getBlank(int number) {
        if (number < BLANKS.size()) {
            return BLANKS.get(number);
        }
        return "";
    }
}
