package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateEngineTest {

    private Template template;
    private Client client = new Client();
    private final TemplateEngine templateEngine = new TemplateEngine();
    private static final String TEMPLATE_WITH_ONE_TAG = "Mail to: #{subject}\n" +
            "**********************************************************************\n" +
            "Dear Employee,\n" +
            "Thank you for your recent resume and letter expressing your interest\n" +
            "in working for our company.\n Will be send message to '#{subject}'";

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
}
