package com.skalashynski.rabbitmq.service;




import com.skalashynski.rabbitmq.command.ProductForm;
import com.skalashynski.rabbitmq.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> listAll();

    Optional<Product> getById(Long id);

    Product saveOrUpdate(Product product);

    void delete(Long id);

    Product saveOrUpdateProductForm(ProductForm productForm);

    void sendProductMessage(String id);

}

