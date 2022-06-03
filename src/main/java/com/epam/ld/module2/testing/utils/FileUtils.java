package com.epam.ld.module2.testing.utils;

import java.io.*;
import java.util.Scanner;

public class FileUtils {

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

    public String readFile(String path) {
        File file = new File(path);
        System.out.println("file.exists(): " + file.exists());
        if (!file.exists()) {
            String absolutePath = this.getClass().getResource("/" + path).getPath();
            file = new File(absolutePath.replace("%20", " "));
        }
        return readFile(file);
    }

    public String readFile(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file, "ISO-8859-1");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

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
