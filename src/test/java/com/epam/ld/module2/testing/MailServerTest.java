package com.epam.ld.module2.testing;

import org.easymock.EasyMock;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import java.util.Arrays;
import java.util.Collection;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailServerTest {
    private final MailServer mailServer = new MailServer();
    private final String mail = "abc@mail.com";

    @TestFactory
    Collection<DynamicTest> sendTestDynamic() {
        return Arrays.asList(
                DynamicTest.dynamicTest("First test to send to console",
                        () -> assertEquals("console", mailServer.sendConsole(mail, "messageContent 1"))),
                DynamicTest.dynamicTest("Second test to send to console",
                        () -> assertEquals("console", mailServer.sendConsole(mail, "messageContent 2"))));
    }

    @UnitTest
    public void shouldSend_WhenTextExist() {
        MailServer mailServer = partialMockBuilder(MailServer.class)
                .addMockedMethod("send")
                .createMock();
        EasyMock.expect(mailServer.send(anyString(), anyString(), anyString())).andReturn("out to file");
        replay(mailServer);
        assertEquals("out to file", mailServer.send("a", "b", "c"));
        assertEquals("console", mailServer.sendConsole("a", "b"));
    }
}
