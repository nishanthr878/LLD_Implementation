package com.lld.fooddelivery.observer;

import com.lld.fooddelivery.model.Order;
import com.lld.fooddelivery.model.enums.OrderStatus;

public class CustomerNotificationObserver implements OrderObserver{

    @Override
    public void onStatusChange(Order order, OrderStatus newStatus) {
        switch (newStatus) {
            case PLACED -> System.out.println("[CUSTOMER} order placed successfully. ID: " + order.getOrderId());
            case CONFIRMED -> System.out.println("[CUSTOMER] your order is confirmed by ");
            case OUT_FOR_DELIVERY -> System.out.println("[CUSTOMER] your order is out for delivery.");
            case DELIVERD -> System.out.println("[CUSTOMER] your order is delivered. Enjoy your meal!");
            case CANCELLED -> System.out.println("[CUSTOMER] your order is cancelled.");
            default -> {}
        }
    }
}
