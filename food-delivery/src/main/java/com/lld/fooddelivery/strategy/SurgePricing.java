package com.lld.fooddelivery.strategy;

import com.lld.fooddelivery.model.Order;

public class SurgePricing implements PricingStrategy {
    private static final double SURGE_MULTIPLIER = 1.5;
    private static final double DELIVERY_FEE = 60.0;

    @Override
    public double calculateTotal(Order order) {
        double itemTotal = order.getItems().stream()
                .mapToDouble(item -> item.getMenuItem().getPrice() * item.getQuantity())
                .sum();
        return (itemTotal * SURGE_MULTIPLIER) + DELIVERY_FEE;
    }
}