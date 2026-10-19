package com.lld.fooddelivery.template;

import com.lld.fooddelivery.model.Order;
import com.lld.fooddelivery.service.OrderService;

public class OnlineOrderProcessor extends OrderProcessor {

    public OnlineOrderProcessor(OrderService orderService) {
        super(orderService);
    }

    @Override
    protected void processPayment(Order order) {
        // Online — charge upfront, simulate payment gateway call
        System.out.println("[ONLINE] Charging " + order.getTotalAmount()
                + " via payment gateway for order: " + order.getOrderId());
        // in real system: call Razorpay/Stripe API here
    }
}
