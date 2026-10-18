package com.lld.fooddelivery.observer;

import com.lld.fooddelivery.model.Order;
import com.lld.fooddelivery.model.enums.OrderStatus;

public class ResturantObserver implements OrderObserver{
    @Override
    public void onStatusChange(Order order, OrderStatus newStatus) {
        switch (newStatus) {
            case CONFIRMED -> System.out.println("[RESTAURANT] New order received. Order ID: " + order.getOrderId() + ". Start preparing.");
            case CANCELLED -> System.out.println("[RESTAURANT] Order ID: " + order.getOrderId() + " has been cancelled.");
            default -> {}
        }
    }
}
