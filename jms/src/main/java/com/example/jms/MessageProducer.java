package com.example.jms;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final JmsTemplate jmsTemplate;

    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.jmsTemplate.setReceiveTimeout(5000); // optional: wait 5 seconds max
    }

    public String sendAndReceive(String destination, String message) {
        Message response = jmsTemplate.sendAndReceive(destination, session ->
                session.createTextMessage(message)
        );

        try {
            if (response instanceof TextMessage textMessage) {
                return textMessage.getText();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return null;
    }
}

