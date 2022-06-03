package com.epam.ld.module2.testing.utils;

import java.io.*;
import java.util.Scanner;

/**
 * The utils for work with files.
 */
public class FileUtils {

    /**
     * Getting the resource from file.
     * @param path the relative path to file from 'resources'
     * @return the String with content
     */
    public String readResource(String path) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(path)){
            Scanner scanner = new Scanner(is, "ISO-8859-1");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Getting the file.
     * @param path the absolute or relative path to file
     * @return the String with content
     */
    public String readFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            String absolutePath = this.getClass().getResource("/" + path).getPath();
            file = new File(absolutePath.replace("%20", " "));
        }
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file, "ISO-8859-1");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Writing content to file.
     * @param fileTo the absolute or relative path to file.
     * @param text the content for writing.
     */
    public void writeFile(File fileTo, String text) {
        try {
            FileWriter fileWriter = new FileWriter(fileTo);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
