package com.skalashynski.rabbitmq.listener;

import com.skalashynski.rabbitmq.config.OrderRabbitMqConfig;
import com.skalashynski.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    @RabbitListener(queues = OrderRabbitMqConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message received from queue");
        System.out.println("Message: " + orderStatus);
    }
}
