package com.skalashynski.rabbitmq.consumer;

import com.skalashynski.rabbitmq.MessageConfig;
import com.skalashynski.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {
    @RabbitListener(queues = MessageConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message received from queue");
        System.out.println("Message: " + orderStatus);

    }
}
