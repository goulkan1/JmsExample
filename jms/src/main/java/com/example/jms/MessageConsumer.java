package com.example.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @JmsListener(destination = "test-queue")
    public String receiveMessage(String message) {
        System.out.println("Received message: " + message);
        return "Reply: " + message.toUpperCase();
    }
}
