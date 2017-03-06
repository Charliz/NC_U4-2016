package com.ncproject.webstore.ejb.beans;

import com.ncproject.webstore.entity.MailEvent;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

import static java.lang.System.out;

/**
 * Created by Champion on 27.02.2017.
 */

@Stateless(name = "TopicSessionBean")
public class TopicSessionBean {

    public TopicSessionBean() {
    }

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/jms/topic/MyTopic")
    private Topic topic;

    public void SendOrderStatus(MailEvent mailEvent) {

        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(topic);

            ObjectMessage objMsg = session.createObjectMessage();
            objMsg.setObject(mailEvent);

            messageProducer.send(objMsg);
            out.println("Sent ObjectMessage to the Topic");
            //session.close();

        } catch (JMSException e) {
            System.out.println("Couldn't send JMS message: ");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException ex) {
                    System.out.println("Couldn't close JMSConnection: ");
                }
            }

        }

    }
}