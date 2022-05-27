package com.epam.ld.module2.testing;


import com.epam.ld.module2.testing.template.FreeTemplates;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Messenger.
 */
public class Messenger {
    private MailServer mailServer;
    private TemplateEngine templateEngine;

    /**
     * Instantiates a new Messenger.
     *
     * @param mailServer     the mail server
     * @param templateEngine the template engine
     */
    public Messenger(MailServer mailServer,
                     TemplateEngine templateEngine) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }

    public static void main(String[] args) {
        MailServer mailServer = new MailServer();
        TemplateEngine templateEngine = new TemplateEngine();
        Messenger messenger = new Messenger(mailServer, templateEngine);
        Client client = messenger.getClient();

        String templateBlank = args.length > 0 ? messenger.getTemplate(args[0]) : messenger.getTemplate();
        messenger.sendMessage(client, new Template(templateBlank), args);
    }

    /**
     * Send message.
     *
     * @param client   the client
     * @param template the template
     */
    public void sendMessage(Client client, Template template, String ...args) {
        String messageContent = templateEngine.generateMessage(template, client);
        if (args.length > 0) {
            mailServer.send(client.getAddresses(), messageContent, args[1]);
        } else {
            mailServer.send(client.getAddresses(), messageContent);
        }
    }

    private String getTemplate() {
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

    private String getTemplate(String fromFile) {
        return Utils.readFile(fromFile);
    }

    private Client getClient() {
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