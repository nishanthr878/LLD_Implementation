package com.lld.payment.decorator;

import com.lld.payment.gateway.PaymentGateway;

public class PaymentGatewayDecorator  implements PaymentGateway {

    protected final PaymentGateway wrapped;

    public PaymentGatewayDecorator(PaymentGateway wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String charge(double amount, String currency, String orderId) {
        return wrapped.charge(amount, currency, orderId);
    }

    @Override
    public String fetchStatus(String paymentId) {
        return wrapped.fetchStatus(paymentId);
    }

    @Override
    public String refund(String paymentId, double amount) {
        return wrapped.refund(paymentId, amount);
    }
}
