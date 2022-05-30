package com.epam.ld.module2.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessengerTest {
    @TempDir
    static Path tempDir;
    private static final String textTemplate  = "Mail to: #{subject}\n" +
            "Dear Student,\n" +
            "Thank you for your interest in working for our company.";

    @Test
    void shouldReturnMock_WhenGetTemplateFromConsole() {
        Messenger mockMessenger = mock(Messenger.class);
        when(mockMessenger.getTemplate()).thenReturn("template from console template");
        assertEquals("template from console template", mockMessenger.getTemplate());
    }

    @Test
    void shouldReturnMock_WhenGetTemplateFromFile() {
        Messenger mockMessenger = mock(Messenger.class);
        when(mockMessenger.getTemplate(Mockito.anyString())).thenReturn("template from file");
        assertEquals("template from file", mockMessenger.getTemplate("a"));
    }

    @Test
    void shouldReturnTemplateFromFile_WhenGetAnyTemplate(){
        Messenger mockMessenger = mock(Messenger.class);
        when(mockMessenger.getTemplate(anyString())).thenReturn(Utils.readResource("./test_template_1.txt"));
        String templateText = mockMessenger.getTemplate("any template");
        assertTrue(templateText.contains("Dear Student"));
    }

    @Test
    void shouldReturnTemplateFromFile_WhenGetAnyTemplate2() throws Exception{
        //create temporary template-file
        Path pathTo = tempDir.resolve("template.txt");
        FileWriter fileWriter = new FileWriter(pathTo.toFile().getCanonicalFile());
        fileWriter.write(textTemplate);
        fileWriter.close();

        //read from temporary template-file
        Path pathFrom = tempDir.resolve("template.txt");
        String actualTemplate = Files.readAllLines(pathFrom).stream()
                .collect(Collectors.joining(System.lineSeparator()));
        assertTrue(actualTemplate.contains("Dear Student"));
    }
}