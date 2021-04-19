package com.skalashynski.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
public class OrderStatus {
    private Order order;
    private String status;//progress, completed
    private String message;

}
