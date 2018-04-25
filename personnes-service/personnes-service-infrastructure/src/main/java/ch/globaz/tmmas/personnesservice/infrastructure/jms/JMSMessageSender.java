package ch.globaz.tmmas.personnesservice.infrastructure.jms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ObjectMessage;


public class JMSMessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(JMSMessageSender.class);

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final String msg) {

       LOGGER.info("Message sending... {}",msg);

       jmsTemplate.send(session -> {
           ObjectMessage objectMessage = session.createObjectMessage(msg);
           LOGGER.info("Message send {}",objectMessage);
           return objectMessage;
       });
    }
}
