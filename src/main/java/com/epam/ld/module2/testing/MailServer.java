package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.utils.FileUtils;
import java.io.File;

/**
 * Mail server class.
 */
public class MailServer {

    private File fileTo;

    /**
     * Send notification.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     */
    public void send(String addresses, String messageContent) {
        if (fileTo == null) {
            System.out.println(messageContent);
            System.out.printf("Mail to %s sent to console.\n", addresses);
        } else {
            new FileUtils().writeFile(fileTo, messageContent);
            System.out.printf("Mail was sent to %s. Content wrote to file '%s'.\n", addresses, fileTo.getPath());
        }
    }

    public void setFileTo(File fileTo) {
        this.fileTo = fileTo;
    }
}
