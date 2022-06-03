package com.epam.ld.module2.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MailServerTest {

    @TempDir
    static Path tempDir;

    MailServer mailServer;

    @BeforeEach
    public void setUpStreams() {
        mailServer = new MailServer();
    }

    @Test
    void shouldSendToConsole_WhenFileIsNull() throws Exception{
        Path filePath = tempDir.resolve("temp.file");
        mailServer.setFileTo(filePath.toFile());
        mailServer.send("name@mail.com", "message content");

        String actualTemplate = Files.readAllLines(filePath).stream()
                .collect(Collectors.joining(System.lineSeparator()));
        assertTrue(actualTemplate.contains("message content"));
    }

    @Test
    void shouldSendToFile_WhenFileExist() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        mailServer.send("name@mail.com", "message content");
        String actual = outContent.toString();

        System.setOut(originalOut);
        assertTrue(actual.contains("message content"));
    }
}
