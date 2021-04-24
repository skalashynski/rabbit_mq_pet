package com.skalashynski.rabbitmq.controller;

import com.skalashynski.rabbitmq.dto.Order;
import com.skalashynski.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.skalashynski.rabbitmq.config.OrderRabbitMqConfig.EXCHANGE;
import static com.skalashynski.rabbitmq.config.OrderRabbitMqConfig.ROUTING_KEY;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        //reustarantService
        //payment service
        OrderStatus orderStatus = new OrderStatus(order, "progress", "order placed sucesfully into in " + restaurantName);
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, orderStatus);
        return "Success !!";
    }
}
