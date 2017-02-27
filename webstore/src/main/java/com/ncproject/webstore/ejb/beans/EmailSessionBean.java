package com.ncproject.webstore.ejb.beans;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

/**
 * Created by Champion on 27.02.2017.
 */

@Stateless(name = "EmailSessionBean")
public class EmailSessionBean {

    public EmailSessionBean() {
    }

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/StatusMessageTopic")
    private Topic statusTopic;

    private String from = "urwinday555@gmail.com";

    public void SendOrderStatus(String to, String subject, String body) {

    try {
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session topicSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer publisher = topicSession.createProducer(statusTopic);

        MapMessage message = topicSession.createMapMessage();

        message.setStringProperty("from", from);
        message.setStringProperty("to", to);
        message.setStringProperty("subject", subject);
        message.setStringProperty("body", body);

        publisher.send(message);

    } catch (JMSException e) {
        e.printStackTrace();
    }
}}


