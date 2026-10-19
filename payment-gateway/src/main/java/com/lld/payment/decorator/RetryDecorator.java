package com.lld.payment.decorator;

import com.lld.payment.gateway.PaymentGateway;

public class RetryDecorator extends PaymentGatewayDecorator {

    private static final int MAX_RETRIES = 3;

    public RetryDecorator(PaymentGateway wrapped) {
        super(wrapped);
    }

    @Override
    public String charge(double amount, String currency, String orderId) {
        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
            try {
                System.out.println("[RETRY] charge attempt " + attempt);
                return super.charge(amount, currency, orderId);
            } catch (Exception e) {
                System.out.println("[RETRY] attempt " + attempt + " failed: " + e.getMessage());
                if (attempt == MAX_RETRIES) throw e;
            }
        }
        throw new RuntimeException("unreachable");
    }

    @Override
    public String fetchStatus(String paymentId) {
        return super.fetchStatus(paymentId);
    }

    @Override
    public String refund(String paymentId, double amount) {
        return super.refund(paymentId, amount);
    }
}
