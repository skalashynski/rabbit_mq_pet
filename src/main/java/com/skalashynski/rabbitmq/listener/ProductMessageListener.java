package com.skalashynski.rabbitmq.listener;


import com.skalashynski.rabbitmq.entity.Product;
import com.skalashynski.rabbitmq.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class ProductMessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMessageListener.class);

    @Autowired
    private ProductRepository productRepository;

    public void receiveMessage(Map<String, String> message) {
        LOGGER.info("Received <{}> message", message);
        Long id = Long.valueOf(message.get("id"));
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(value -> productRepository.save(value));
        LOGGER.info("Sent all emails");
    }

}
