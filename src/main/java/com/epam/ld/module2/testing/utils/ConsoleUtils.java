package com.epam.ld.module2.testing.utils;

import com.epam.ld.module2.testing.Client;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The utils for input from Console.
 */
public class ConsoleUtils {

    /**
     * Getting the Template from free Templates.
     *
     * @return the String
     */
    public String getTemplate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chose template. Input one of values: 1 or 2 or 3 --> ");
        while (true) {
            String templateNumber = scanner.nextLine();
            try {
                int number = Integer.parseInt(templateNumber);
                if (number > 3) {
                    throw new NumberFormatException("Number more than 3.");
                }
                return FreeTemplates.getBlank(number - 1);
            } catch (NumberFormatException e) {
                System.out.println("You input invalid. Please, try again (1 or 2 or 3) --> ");
            }
        }
    }

    /**
     * Getting the Client from console.
     *
     * @return the new Client.
     */
    public Client getClient() {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();
        System.out.print("Input your Email --> ");
        while (true) {
            String email = scanner.nextLine();
            Matcher matcher = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(email);
            if (matcher.find()) {
                client.setAddresses(email);
                return client;
            }
            System.out.print("Your input email invalid. Try input Email again --> ");
        }
    }
}
