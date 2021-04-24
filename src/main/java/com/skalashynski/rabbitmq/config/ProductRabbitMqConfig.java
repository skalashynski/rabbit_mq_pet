package com.skalashynski.rabbitmq.config;

import com.skalashynski.rabbitmq.listener.ProductMessageListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductRabbitMqConfig {
    public static final String SFG_MESSAGE_QUEUE = "sqg-message-queue";

    @Bean
    Queue queue() {
        return new Queue(SFG_MESSAGE_QUEUE, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(SFG_MESSAGE_QUEUE);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(SFG_MESSAGE_QUEUE);
        container.setMessageListener(messageListenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(ProductMessageListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
