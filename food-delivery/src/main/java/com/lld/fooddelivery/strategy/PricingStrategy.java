package com.lld.fooddelivery.strategy;

import com.lld.fooddelivery.model.Order;

public interface PricingStrategy {
    double calculateTotal(Order order);
}