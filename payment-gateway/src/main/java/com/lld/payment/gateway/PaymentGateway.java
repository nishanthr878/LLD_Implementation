package com.lld.payment.gateway;

public interface PaymentGateway {
    String charge(double amount, String currency, String orderId);
    String fetchStatus(String paymentId);
    String refund(String paymentId, double amount);
}
