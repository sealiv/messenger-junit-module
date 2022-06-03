package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.MessengerTestWatcher;
import com.epam.ld.module2.testing.UnitTest;
import com.epam.ld.module2.testing.utils.TagUtils;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MessengerTestWatcher.class)
public class TemplateEngineTest {

    private final TemplateEngine templateEngine = new TemplateEngine();

    @UnitTest
    void generateMessage() throws Exception {
        Template template = new Template("Dear #{name}, Thank you for your expressing for #{companyName}.");

        TagUtils mockTagUtils = mock(TagUtils.class);
        Map<String, String> param = new HashMap<>();
        param.put("name", "paramA");
        param.put("companyName", "paramB");

        when(mockTagUtils.getTagValues(anySet())).thenReturn("paramA paramB");
        when(mockTagUtils.getParams(anySet(), anyString())).thenReturn(param);

        Field params = TemplateEngine.class.getDeclaredField("tagUtils");
        params.setAccessible(true);
        params.set(templateEngine, mockTagUtils);

        String completeTemplate = templateEngine.generateMessage(template);
        assertAll(()-> assertFalse(completeTemplate.contains("#{name}")),
                  ()-> assertFalse(completeTemplate.contains("#{companyName}")));
    }

    @UnitTest
    void fillTagsTest() {
        Map<String, String> params = new HashMap<>();
        Template template = new Template("Dear #{name}, Thank you for your expressing for #{companyName}.");
        params.put("name", "Alex");
        params.put("companyName", "MMM-Invest");
        String actualTemplate = templateEngine.fillTags(template, params);

        String expected = "Dear Alex, Thank you for your expressing for MMM-Invest.";
        assertEquals(expected, actualTemplate);
    }

    @UnitTest
    void findRawTagsTest() {
        Set<String> tags = templateEngine.findRawTags("The #{d}#{b} {wrong} middle#{a}s #{d}");
        assertEquals("[a, b, d]", tags.toString());
    }
}
