package com.lld.fooddelivery.strategy;

import com.lld.fooddelivery.model.Order;

public class LoyaltyPricing implements PricingStrategy {
    private static final double DISCOUNT = 0.10;
    private static final double DELIVERY_FEE = 0.0;  // free delivery for loyalty

    @Override
    public double calculateTotal(Order order) {
        double itemTotal = order.getItems().stream()
                .mapToDouble(item -> item.getMenuItem().getPrice() * item.getQuantity())
                .sum();
        return itemTotal - (itemTotal * DISCOUNT) + DELIVERY_FEE;
    }
}