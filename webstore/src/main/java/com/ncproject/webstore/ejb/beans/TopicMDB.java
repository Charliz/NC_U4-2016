package com.ncproject.webstore.ejb.beans;


import com.ncproject.webstore.entity.MailEvent;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * Created by Champion on 27.02.2017.
 */

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(
                propertyName = "destination", propertyValue = "java:/jms/topic/MyTopic") })


public class TopicMDB implements javax.jms.MessageListener {
    @Resource(mappedName = "java:jboss/mail/Gmail")
    private Session mailSession;

    public void onMessage(Message message){
        try {
            if (message instanceof ObjectMessage) {
                System.out.println("Topic: I received an ObjectMessage " +
                        " at " + new Date());
                ObjectMessage objectMessage = (ObjectMessage) message;
                MailEvent mailEvent = (MailEvent) objectMessage.getObject();

                InternetAddress fromAddress = InternetAddress.getLocalAddress(mailSession);
                String fromString = fromAddress.getAddress();

                javax.mail.Message msg = new MimeMessage(mailSession);

                msg.setFrom(new InternetAddress(fromString,"NC Onlinestore"));
                InternetAddress[] address = {new InternetAddress(mailEvent.get_To())};
                msg.setRecipients(javax.mail.Message.RecipientType.TO, address);
                msg.setSubject(mailEvent.getSubject());
                msg.setSentDate(new java.util.Date());
                msg.setContent(mailEvent.getMessage(), "text/html");
                System.out.println("MDB: Sending Message...");
                mailSession.setDebug(true);

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

