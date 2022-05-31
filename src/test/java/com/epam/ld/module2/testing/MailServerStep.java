package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.MailServer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.easymock.EasyMock;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailServerStep {

    private MailServer mailServer;

    @Given("Create partition MailServer Mock with mocked method 'send'")
    public void createPartitionMailServerMockWithMockedMethodSend() {
        mailServer = partialMockBuilder(MailServer.class)
                .addMockedMethod("send")
                .createMock();
    }

    @When("mock method should return {string} when it call")
    public void mockMethodShouldReturnWhenItCall(String arg0) {
        EasyMock.expect(mailServer.send(anyString(), anyString(), anyString())).andReturn(arg0);
        replay(mailServer);
    }

    @Then("check call original method with 2 parameters: {string}, {string} should return {string}")
    public void checkCallOriginalMethodWhenParameters(String arg1, String arg2, String expected) {
        assertEquals(expected, mailServer.sendConsole(arg1, arg2));
    }

    @And("check call mock method when 3 parameters: {string}, {string}, {string} should return {string}")
    public void checkCallMockMethodWhenParameters(String arg1, String arg2, String arg3, String expected) {
        assertEquals(expected, mailServer.send(arg1, arg2, arg3));
    }
}
