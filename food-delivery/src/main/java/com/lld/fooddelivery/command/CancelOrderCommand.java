package com.lld.fooddelivery.command;

import com.lld.fooddelivery.model.Order;
import com.lld.fooddelivery.model.enums.OrderStatus;
import com.lld.fooddelivery.service.OrderService;

public class CancelOrderCommand implements OrderCommand {

    private final Order order;
    private final OrderService orderService;
    private OrderStatus prviousStatus;

    public CancelOrderCommand(Order order, OrderService orderService) {
        this.order = order;
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        prviousStatus = order.getStatus();
        orderService.updateStatus(order, OrderStatus.CANCELLED);
    }

    @Override
    public void undo() {
        if (prviousStatus != null) {
            orderService.updateStatus(order, prviousStatus);
        }
    }
}
