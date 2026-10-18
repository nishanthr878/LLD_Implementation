package com.lld.fooddelivery.observer;

import com.lld.fooddelivery.model.Order;
import com.lld.fooddelivery.model.enums.OrderStatus;

public interface OrderObserver {
    void onStatusChange(Order order, OrderStatus newStatus);
}
