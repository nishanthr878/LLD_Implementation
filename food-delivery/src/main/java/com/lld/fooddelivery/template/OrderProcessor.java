package com.lld.fooddelivery.template;

import com.lld.fooddelivery.model.Order;
import com.lld.fooddelivery.model.enums.OrderStatus;
import com.lld.fooddelivery.service.OrderService;

public abstract class OrderProcessor {

    protected final OrderService orderService;

    public OrderProcessor(OrderService orderService) {
        this.orderService = orderService;
    }

    // The Template Method - skeleton is fixed, final so no subclass can override it
    public final void processOrder(Order order) {
        validateOrder(order);
        processPayment(order);
        notifyRestaurant(order);
        assignDeliveryAgent(order);
    }

    // fixed step - same for all payement methods
    private void validateOrder(Order order) {
        if(order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalStateException("Order has no items");
        }
        System.out.println("[PROCESSOR] order validated: " + order.getItems());
    }

    // variable step - each subclass implement this differently
    protected abstract void processPayment(Order order);

    // fixed step - same for all payement methods
    private void notifyRestaurant(Order order) {
        orderService.updateStatus(order, OrderStatus.CONFIRMED);
        System.out.println("[PROCESSOR] Resturant notified for order: " + order.getOrderId());
    }

    // fixed step - same for all payement methods
    private void assignDeliveryAgent(Order order) {
        orderService.updateStatus(order, OrderStatus.PREPARING);
        System.out.println("[PROCESSOR] Delivery agent assigned for order: " + order.getOrderId());
    }

}
