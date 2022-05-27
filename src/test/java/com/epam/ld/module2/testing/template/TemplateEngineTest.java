package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exception.LeftRawTagsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TemplateEngineTest {

    private Template template;
    private Client client = new Client();
    private final TemplateEngine templateEngine = new TemplateEngine();
    private static final String TEMPLATE_WITH_ONE_TAG = "Mail to: #{subject}\n" +
            "**********************************************************************\n" +
            "Dear Employee,\n" +
            "Thank you for your recent resume and letter expressing your interest\n" +
            "in working for our company.\n Will be send message to '#{subject}'";

    private static final String TEMPLATE_WITH_THREE_TAGS = "Mail to: #{subject}\n" +
            "**********************************************************************\n" +
            "Dear #{name},\n" +
            "Thank you for your recent resume and letter expressing your interest\n" +
            "in working for #{companyName}.\n Will be send message to '#{subject}'";

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setAddresses("abc@mail.com");
    }

    @Test
    public void shouldChangeTagToValue_WhenGenerateMessage() {
        template = new Template(TEMPLATE_WITH_ONE_TAG);
        String actual = templateEngine.generateMessage(template, client);
        assertTrue(actual.contains(client.getAddresses()));
    }

    @Test
    public void shouldThrowException_WhenLeftTags() {
        template = new Template(TEMPLATE_WITH_THREE_TAGS);

        Template spyTemplate = spy(template);
        doNothing().when(spyTemplate).getTagValueFromConsole();

        assertThrows(LeftRawTagsException.class, () -> templateEngine.generateMessage(spyTemplate, client));
        verify(spyTemplate, times(1)).getTagValueFromConsole();
    }

    @Test
    public void shouldReturnTrue_WhenTagsMoreThenContainsTemplate() throws Exception {
        template = new Template(TEMPLATE_WITH_THREE_TAGS);

        Template spyTemplate = spy(template);

        Field params = Template.class.getDeclaredField("params");
        params.setAccessible(true);
        Map<String, String> newParams = new HashMap<>();
        newParams.put("name", "Dmitry");
        newParams.put("companyName", "'Google'");
        newParams.put("country", "USA");
        params.set(spyTemplate, newParams);

        doNothing().when(spyTemplate).getTagValueFromConsole();

        assertDoesNotThrow(() -> templateEngine.generateMessage(spyTemplate, client));
    }
}
