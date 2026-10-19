package com.lld.fooddelivery.command;

import com.lld.fooddelivery.model.Order;
import com.lld.fooddelivery.model.enums.OrderStatus;
import com.lld.fooddelivery.service.OrderService;

public class PlaceOrderCommand implements OrderCommand {

    private final Order order;
    private final OrderService orderService;
    private OrderStatus previousStatus;

    public PlaceOrderCommand(Order order, OrderService orderService) {
        this.order = order;
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        previousStatus = order.getStatus();
        orderService.placeOrder(order);
    }

    @Override
    public void undo() {
        if (previousStatus != null) {
            orderService.updateStatus(order, previousStatus);
        }
    }
}
