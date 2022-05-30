package com.epam.ld.module2.testing;

/**
 * Mail server class.
 */
public class MailServer {

    /**
     * Send notification.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     */
    public String sendConsole(String addresses, String messageContent) {
        System.out.println(messageContent);
        System.out.printf("Mail to %s sent.\n", addresses);
        return "console";
    }

    public String send(String addresses, String messageContent, String args) {
        Utils.writeFile(args, messageContent);
        System.out.printf("Mail to %s sent.\n", addresses);
        return args;
    }
}
