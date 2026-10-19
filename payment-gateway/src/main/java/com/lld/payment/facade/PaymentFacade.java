package com.lld.payment.facade;

import com.lld.payment.decorator.LoggingDecorator;
import com.lld.payment.decorator.RetryDecorator;
import com.lld.payment.external.RazorpayClient;
import com.lld.payment.gateway.PaymentGateway;
import com.lld.payment.gateway.RazorpayGateway;

public class PaymentFacade {

    private final PaymentGateway gateway;

    public PaymentFacade() {
        // all the writing happends here - caller never sees this
        PaymentGateway base = new RazorpayGateway(new RazorpayClient());
        PaymentGateway withRetry = new RetryDecorator(base);
        this.gateway = new LoggingDecorator(withRetry);
    }

    public String charge(double amount, String currency, String orderId) {
        return gateway.charge(amount, currency, orderId);
    }

    public String fetchStatus(String paymentId) {
        return gateway.fetchStatus(paymentId);
    }

    public String refund(String paymentId, double amount) {
        return gateway.refund(paymentId, amount);
    }
}
