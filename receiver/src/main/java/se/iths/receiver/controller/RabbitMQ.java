package se.iths.receiver.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQ {

    @RabbitListener(queues = "${auktionera.rabbitmq.queue}")
    public void recievedMessage(String incomingMessage) {
        System.out.println("Recieved Message from RabbitMQ: " + incomingMessage );
    }
}
