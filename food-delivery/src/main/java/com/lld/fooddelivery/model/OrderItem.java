package com.lld.fooddelivery.model;

import lombok.Data;

@Data
public class OrderItem {
    private String orderItemId;
    private MenuItem menuItem;
    private int quantity;
    private double itemTotal;
}
