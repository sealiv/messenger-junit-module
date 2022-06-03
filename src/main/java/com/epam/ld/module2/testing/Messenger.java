package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import com.epam.ld.module2.testing.utils.ConsoleUtils;
import com.epam.ld.module2.testing.utils.FileUtils;

import java.io.File;

/**
 * The type Messenger.
 */
public class Messenger {
    private final MailServer mailServer;
    private final TemplateEngine templateEngine;

    /**
     * Instantiates a new Messenger.
     *
     * @param mailServer     the mail server
     * @param templateEngine the template engine
     * @param args the names of file template and file to send
     */
    public Messenger(MailServer mailServer, TemplateEngine templateEngine, String ...args) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
        if (args.length > 1) {
            mailServer.setFileTo(new File(args[1]));
        }
    }

    /**
     * Main class
     *
     * @param args the names of file template and file to send
     */
    public static void main(String[] args) {
        ConsoleUtils consoleUtils = new ConsoleUtils();
        FileUtils fileUtils = new FileUtils();
        Messenger messenger = new Messenger(new MailServer(), new TemplateEngine(), args);
        Client client = consoleUtils.getClient();
        Template template = args.length > 0
                ? new Template(fileUtils.readFile(args[0]))
                : new Template(consoleUtils.getTemplate());
        messenger.sendMessage(client, template);
    }

    /**
     * Send message.
     *
     * @param client   the client
     * @param template the template
     */
    public void sendMessage(Client client, Template template) {
        String messageContent = templateEngine.generateMessage(template);
        mailServer.send(client.getAddresses(), messageContent);
    }
}