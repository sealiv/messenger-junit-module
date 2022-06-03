package com.epam.ld.module2.testing.utils;

import com.epam.ld.module2.testing.exception.LeftRawTagsException;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TagUtilsTest {
    private final TagUtils tagUtils = new TagUtils();

    @Test
    void getParamsTest() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("aa", "paramA");
        expectedMap.put("bb", "paramB");

        Set<String> tags = new TreeSet<>(Arrays.asList("aa", "bb"));
        String tagValues = "paramA paramB";
        Map<String, String> actualMap = tagUtils.getParams(tags, tagValues);

        assertEquals(expectedMap, actualMap);
    }

    @Test
    void getParams_ShouldReturnException_WhenLeftRawTags() {
        Set<String> tags = new TreeSet<>(Arrays.asList("aa", "bb"));
        String tagValues = "paramA";

        assertThrows(LeftRawTagsException.class, ()-> tagUtils.getParams(tags, tagValues));
    }

    @Test
    void getParams_ShouldDoesNotException_WhenPassMoreParametersThanExistRawTags() {
        Set<String> tags = new TreeSet<>(Arrays.asList("aa", "bb"));
        String tagValues = "paramA paramB paramC paramD";

        assertDoesNotThrow(()-> tagUtils.getParams(tags, tagValues));
    }
}