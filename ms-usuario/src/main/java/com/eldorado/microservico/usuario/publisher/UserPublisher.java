package com.eldorado.microservico.usuario.publisher;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPublisher {

    private RabbitTemplate rabbitTemplate;

    private Queue queue;

    private final String exchange = "user-service";
    private final String createRouting = "create-routing";
    private final String updateRouting = "update-routing";

    @Autowired
    public UserPublisher(RabbitTemplate template, Queue queue){
        this.rabbitTemplate = template;
        this.queue = queue;
        rabbitTemplate.setExchange(exchange);
    }

    public void createMessage(String message){
        rabbitTemplate.setRoutingKey(createRouting);
        this.sendToQueue(message);
    }

    public void updateMessage(String message){
        rabbitTemplate.setRoutingKey(createRouting);
        this.sendToQueue(message);
    }

    private void sendToQueue(String message) {
        rabbitTemplate.convertAndSend(message);
    }

}
