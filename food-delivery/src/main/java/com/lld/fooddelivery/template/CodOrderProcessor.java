package com.lld.fooddelivery.template;

import com.lld.fooddelivery.model.Order;
import com.lld.fooddelivery.service.OrderService;

public class CodOrderProcessor extends OrderProcessor {

    public CodOrderProcessor(OrderService orderService) {
        super(orderService);
    }

    @Override
    protected void processPayment(Order order) {
        // COD - no upfront payment, just mark as pending
        System.out.println("[COD] Payment to be collected on delivery. Amount: " + order.getTotalAmount());
    }
}
