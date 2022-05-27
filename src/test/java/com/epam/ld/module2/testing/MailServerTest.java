package com.epam.ld.module2.testing;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailServerTest {
    private final MailServer mailServer = new MailServer();
    private final String mail = "abc@mail.com";

    @TestFactory
    Collection<DynamicTest> sendTestDynamic() {
        return Arrays.asList(
                DynamicTest.dynamicTest("First test to send to console",
                        () -> assertEquals("console", mailServer.send(mail, "messageContent 1"))),
                DynamicTest.dynamicTest("Second test to send to console",
                        () -> assertEquals("console", mailServer.send(mail, "messageContent 2"))));
    }
}
