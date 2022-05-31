package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.Utils;
import com.epam.ld.module2.testing.exception.LeftRawTagsException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TemplateEngineStep {
    private Template template;
    private List<String> templateContainer;
    private final Client client = new Client();
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

    @Given("Create template with tags {string}")
    public void createTemplateWithOneTag(String arg0) throws NoSuchFieldException, IllegalAccessException {
        java.lang.reflect.Field fieldName = this.getClass().getDeclaredField(arg0);
        fieldName.setAccessible(true);
        String rawTemplate = (String) fieldName.get(this);
        template = new Template(rawTemplate);
    }

    @Given("Create Client with email {string}")
    public void createClientWithEmail(String arg0) {
        client.setAddresses(arg0);
    }

    @When("do nothing when application try to find tags and to change its to their values")
    public void doNothingWhenApplicationTryToFindTagsAndToChangeItsToTheirValues() {
        template = spy(template);
        doNothing().when(template).getTagValueFromConsole();
    }

    @When("pass more parameters than exists raw tags")
    public void passMoreParametersThanExistsRawTags() throws Exception {
        Field params = Template.class.getDeclaredField("params");
        params.setAccessible(true);
        Map<String, String> newParams = new HashMap<>();
        newParams.put("name", "Dmitry");
        newParams.put("companyName", "'Google'");
        newParams.put("country", "USA");
        params.set(template, newParams);
    }

    @Then("should find email in template after generateMessage")
    public void shouldFindFragmentInTemplate() {
        String actual = templateEngine.generateMessage(template, client);
        assertTrue(actual.contains(client.getAddresses()), "email address '" + client.getAddresses() + "' in template not find");
    }

    @Then("should throw exception when raw tags is present")
    public void shouldThrowExceptionWhenRawTagsIsPresent() {
        assertThrows(LeftRawTagsException.class, () -> templateEngine.generateMessage(template, client));
        verify(template, times(1)).getTagValueFromConsole();
    }

    @Then("should process without exception when was passed more parameters than exists raw tags")
    public void shouldProcessWithoutExceptionWhenWasPassedMoreParametersThanExistsRawTags() {
        assertDoesNotThrow(() -> templateEngine.generateMessage(template, client));
    }

    @Given("We have three file with templates")
    public void weHaveThreeFileWithTemplates(List<String> files) {
        templateContainer = files.stream()
                .map(Utils::readResource)
                .collect(Collectors.toList());
    }

    @Then("should find tag {string} in each template")
    public void shouldFindTagInEachTemplate(String arg0) {
        for (String s : templateContainer) {
            assertTrue(s.contains(arg0));
        }
    }
}
