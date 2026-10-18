package com.lld.fooddelivery.strategy;

import com.lld.fooddelivery.model.Order;

public class RegularPricing implements PricingStrategy {
    private static final double DELIVERY_FEE = 30.0;

    @Override
    public double calculateTotal(Order order) {
        double itemTotal = order.getItems().stream()
                .mapToDouble(item -> item.getMenuItem().getPrice() * item.getQuantity())
                .sum();
        return itemTotal + DELIVERY_FEE;
    }
}