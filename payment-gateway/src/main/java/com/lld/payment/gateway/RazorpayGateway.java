package com.lld.payment.gateway;

import com.lld.payment.external.RazorpayClient;

public class RazorpayGateway implements PaymentGateway {

    private final RazorpayClient client;

    public RazorpayGateway(RazorpayClient client) {
        this.client = client;
    }

    @Override
    public String charge(double amount, String currency, String orderId) {
        // translate our method -> RazorpayClient's method
        return client.initiateTransaction(amount, currency, orderId);
    }

    @Override
    public String fetchStatus(String paymentId) {
        return client.getTransactionStatus(paymentId);
    }

    @Override
    public String refund(String paymentId, double amount) {
        return client.initiateRefund(paymentId, amount);
    }
}
