package com.lld.payment.gateway;

import com.lld.payment.external.StripeClient;

import java.util.HashMap;
import java.util.Map;

public class StripeGateway implements PaymentGateway {

    private final StripeClient client;
    private final Map<String, String> paymentIntentToOrder = new HashMap<>();

    public StripeGateway(StripeClient client) {
        this.client = client;
    }

    @Override
    public String charge(double amount, String currency, String orderId) {
        // Stripe doesn't use orderid in createpaymentIntent - we just drop it
        String paymentIntentId = client.createPaymentIntent(amount, currency);
        paymentIntentToOrder.put(paymentIntentId, orderId);
        return paymentIntentId;
    }

    @Override
    public String fetchStatus(String paymentId) {
        return client.retrievePaymentIntent(paymentId);
    }

    @Override
    public String refund(String paymentId, double amount) {
        return client.createRefund(paymentId, amount);
    }
}
