package com.skalashynski.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
public class Order {
    private String orderId;
    private String name;
    private String quantity;
    private String price;
}
