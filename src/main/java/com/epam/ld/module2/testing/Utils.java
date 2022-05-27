package com.epam.ld.module2.testing;

import java.io.*;
import java.util.Scanner;

public final class Utils {

    public static String readResource(String path) {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = Utils.class.getClassLoader().getResourceAsStream(path)){
            Scanner scanner = new Scanner(is, "ISO-8859-1");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String readFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file, "ISO-8859-1");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void writeFile(String path, String text) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
