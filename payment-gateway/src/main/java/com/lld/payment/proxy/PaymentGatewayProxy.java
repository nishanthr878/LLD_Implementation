package com.lld.payment.proxy;

import com.lld.payment.gateway.PaymentGateway;

import java.util.Set;

public class PaymentGatewayProxy implements PaymentGateway {

    private final PaymentGateway real;
    private final String merchantId;

    private static final Set<String> AUTHORIZED_MERCHANTS = Set.of("merchant_001", "merchant_002");

    public PaymentGatewayProxy(PaymentGateway real, String merchantId) {
        this.real = real;
        this.merchantId = merchantId;
    }

    private void checkAuthorization() {
        if(!AUTHORIZED_MERCHANTS.contains(merchantId)) {
            throw new SecurityException("Unauthorized merchant: " + merchantId);
        }
    }

    @Override
    public String charge(double amount, String currency, String orderId) {
        checkAuthorization();
        System.out.println("[PROXY] Merchant " + merchantId + " authorized. Forwarding charge.");
        return real.charge(amount, currency, orderId);
    }

    @Override
    public String fetchStatus(String paymentId) {
        checkAuthorization();
        System.out.println("[PROXY] Merchant " + merchantId + " authorized. Forwarding status fetch.");
        return real.fetchStatus(paymentId);
    }

    @Override
    public String refund(String paymentId, double amount) {
        checkAuthorization();
        System.out.println("[PROXY] Merchant " + merchantId + " authorized. Forwarding refund.");
        return real.refund(paymentId, amount);
    }
}
