package se.iths.receiver.controller;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import se.iths.receiver.business.service.IUserLogService;

@Component
public class RabbitMQ {

    private final IUserLogService userLogService;
    private JSONParser parser;

    public RabbitMQ(IUserLogService userLogService) {
        this.userLogService = userLogService;
    }

    @RabbitListener(queues = "${auktionera.rabbitmq.queue}")
    public void recievedMessage(String message) throws ParseException, JSONException {
        JSONObject obj = (JSONObject) parser.parse(message);
        userLogService.createUserLog(obj.getString("userName"), obj.getLong("id"));
        System.out.println("Recieved Message from RabbitMQ: " + obj);
    }
}

