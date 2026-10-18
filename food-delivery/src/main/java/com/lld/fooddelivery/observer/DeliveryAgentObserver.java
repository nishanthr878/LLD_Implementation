package com.lld.fooddelivery.observer;

import com.lld.fooddelivery.model.Order;
import com.lld.fooddelivery.model.enums.OrderStatus;

public class DeliveryAgentObserver implements OrderObserver {
    @Override
    public void onStatusChange(Order order, OrderStatus newStatus) {
        switch (newStatus) {
            case PREPARING -> System.out.println("[DELIVERY AGENT] Order ready soon. Head to: "
                    + order.getRestaurant().getAddress());
            case CANCELLED -> System.out.println("[DELIVERY AGENT] Order cancelled. Stand down.");
            default -> {}
        }
    }
}