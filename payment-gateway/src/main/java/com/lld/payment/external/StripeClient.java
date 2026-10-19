package com.lld.payment.external;

public class StripeClient {

    public String createPaymentIntent(double amount, String currency) {
        System.out.println("[STRIPE SDK] Creating PaymentIntent: $"
                + amount + " " + currency);
        return "pi_" + System.currentTimeMillis();
    }

    public String retrievePaymentIntent(String paymentIntentId) {
        System.out.println("[STRIPE SDK] Retrieving PaymentIntent: " + paymentIntentId);
        return "SUCCEEDED";
    }

    public String createRefund(String paymentIntentId, double amount) {
        System.out.println("[STRIPE SDK] Creating refund of $"
                + amount + " for: " + paymentIntentId);
        return "re_" + System.currentTimeMillis();
    }
}