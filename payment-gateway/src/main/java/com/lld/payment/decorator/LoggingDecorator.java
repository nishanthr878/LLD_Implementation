package com.lld.payment.decorator;

import com.lld.payment.gateway.PaymentGateway;

public class LoggingDecorator extends PaymentGatewayDecorator {

    public LoggingDecorator(PaymentGateway wrapped) {
        super(wrapped);
    }

    @Override
    public String charge(double amount, String currency, String orderId) {
        System.out.println("[LOG] charge() called — amount: " + amount + " currency: " + currency + " orderId: " + orderId);
        String result = super.charge(amount, currency, orderId);
        System.out.println("[LOG] charge() result — paymentId: " + result);
        return result;
    }

    @Override
    public String fetchStatus(String paymentId) {
        System.out.println("[LOG] fetchStatus() called — paymentId: " + paymentId);
        String result = super.fetchStatus(paymentId);
        System.out.println("[LOG] fetchStatus() result — status: " + result);
        return result;
    }

    @Override
    public String refund(String paymentId, double amount) {
        System.out.println("[LOG] refund() called — paymentId: " + paymentId
                + " amount: " + amount);
        String result = super.refund(paymentId, amount);
        System.out.println("[LOG] refund() result — refundId: " + result);
        return result;
    }
}
