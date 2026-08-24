package com.lld.fooddelivery.model;

import com.lld.fooddelivery.model.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private String orderId;
    private Customer customer;
    private Restaurant restaurant;
    private List<OrderItem> items;
    private OrderStatus status;
    private double totalAmount;
    private LocalDateTime placedAt;
    private LocalDateTime updatedAt;
}
