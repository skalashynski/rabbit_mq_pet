package com.skalashynski.rabbitmq.service;


import com.skalashynski.rabbitmq.command.ProductForm;
import com.skalashynski.rabbitmq.config.ProductRabbitMqConfig;
import com.skalashynski.rabbitmq.converter.ProductFormToProduct;
import com.skalashynski.rabbitmq.entity.Product;
import com.skalashynski.rabbitmq.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductFormToProduct productFormToProduct;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product saveOrUpdateProductForm(ProductForm productForm) {
        Product savedProduct = saveOrUpdate(productFormToProduct.convert(productForm));
        return savedProduct;
    }

    @Override
    public void sendProductMessage(String id) {
        Map<String, String> actionMap = new HashMap<>();
        actionMap.put("id", id);
        LOGGER.info("Sending the index request through queue message");
        rabbitTemplate.convertAndSend(ProductRabbitMqConfig.SFG_MESSAGE_QUEUE, actionMap);
    }
}
