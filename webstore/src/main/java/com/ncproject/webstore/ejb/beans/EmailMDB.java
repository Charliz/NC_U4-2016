package com.ncproject.webstore.ejb.beans;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Champion on 27.02.2017.
 */

@MessageDriven(activationConfig= {
@ActivationConfigProperty(propertyName="destinationName",
        propertyValue="java:/StatusMessageTopic"),
@ActivationConfigProperty(propertyName="destinationType",
        propertyValue="javax.jms.Topic")
}, mappedName="StatusMessageTopic")


public class EmailMDB implements javax.jms.MessageListener {
    @Resource(mappedName = "java:jboss/mail/Gmail")
    private Session mailSession;

    public void onMessage(Message message){
        try {
            if (message instanceof MapMessage) {
                MapMessage orderMessage = (MapMessage)message;
                String from = orderMessage.getStringProperty("from");
                String to = orderMessage.getStringProperty("to");
                String subject = orderMessage.getStringProperty("subject");
                String body = orderMessage.getStringProperty("body");

                javax.mail.Message msg = new MimeMessage(mailSession);

                msg.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(javax.mail.Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setSentDate(new java.util.Date());
                msg.setContent(body, "text/html");
                System.out.println("MDB: Sending Message...");
                Transport.send(msg);
                System.out.println("MDB: Message Sent");
            }
            else {
                System.out.println("Invalid message ");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

