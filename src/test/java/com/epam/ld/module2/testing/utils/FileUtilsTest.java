package com.epam.ld.module2.testing.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    private final FileUtils fileUtils = new FileUtils();
    @TempDir
    static Path tempDir;

    @Test
    void readResource() {
        String fileContent = fileUtils.readResource("test_template.txt");
        assertTrue(fileContent.startsWith("test_template.txt: Dear #{receiverName}"));
    }

    @Test
    void readFile_WhenPassStringPath() {
        String fileContent = fileUtils.readFile("test_template.txt");
        assertTrue(fileContent.startsWith("test_template.txt: Dear #{receiverName}"));
    }

    @Test
    void writeFile() {
        Path pathTo = tempDir.resolve("new_file_to.tmp");
        fileUtils.writeFile(pathTo.toFile(), "file content");
        assertEquals(12, pathTo.toFile().length());
    }
}