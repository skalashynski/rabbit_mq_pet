package com.skalashynski.rabbitmq.repository;


import com.skalashynski.rabbitmq.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
