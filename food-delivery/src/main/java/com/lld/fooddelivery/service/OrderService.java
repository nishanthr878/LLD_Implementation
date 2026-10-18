package com.lld.fooddelivery.service;

import com.lld.fooddelivery.model.Order;
import com.lld.fooddelivery.model.enums.OrderStatus;
import com.lld.fooddelivery.observer.OrderObserver;
import com.lld.fooddelivery.strategy.PricingStrategy;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    // Observer list
    private final List<OrderObserver> observers = new ArrayList<>();

    // Startegy - injected, not hardcoded
    private PricingStrategy pricingStrategy;

    public OrderService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Order order, OrderStatus newStatus) {
        for (OrderObserver observer : observers) {
            observer.onStatusChange(order, newStatus);
        }
    }


    public void placeOrder(Order order) {
        double total = pricingStrategy.calculateTotal(order);
        order.setTotalAmount(total);
        updateStatus(order, OrderStatus.PLACED);
    }

    public void updateStatus(Order order, OrderStatus newStatus) {
        order.setStatus(newStatus);
        order.setUpdatedAt(java.time.LocalDateTime.now());
        System.out.println("[ORDER] Status updated to: " + newStatus + " for order: " + order.getOrderId());
        notifyObservers(order, newStatus);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

}
