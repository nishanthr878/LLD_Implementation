package com.lld.payment.controller;

import com.lld.payment.decorator.LoggingDecorator;
import com.lld.payment.decorator.RetryDecorator;
import com.lld.payment.external.RazorpayClient;
import com.lld.payment.facade.PaymentFacade;
import com.lld.payment.gateway.PaymentGateway;
import com.lld.payment.gateway.RazorpayGateway;
import com.lld.payment.proxy.PaymentGatewayProxy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    // Facade — hides all wiring
    private final PaymentFacade facade = new PaymentFacade();

    // Proxy — authorized merchant
    private final PaymentGateway authorizedGateway = new PaymentGatewayProxy(
            new LoggingDecorator(
                    new RetryDecorator(
                            new RazorpayGateway(new RazorpayClient())
                    )
            ),
            "merchant_001"
    );

    // Proxy — unauthorized merchant
    private final PaymentGateway unauthorizedGateway = new PaymentGatewayProxy(
            new RazorpayGateway(new RazorpayClient()),
            "merchant_999"
    );

    @PostMapping("/charge")
    public String charge(@RequestParam double amount,
                         @RequestParam String currency,
                         @RequestParam String orderId) {
        return facade.charge(amount, currency, orderId);
    }

    @GetMapping("/status")
    public String status(@RequestParam String paymentId) {
        return facade.fetchStatus(paymentId);
    }

    @PostMapping("/refund")
    public String refund(@RequestParam String paymentId,
                         @RequestParam double amount) {
        return facade.refund(paymentId, amount);
    }

    @PostMapping("/charge-authorized")
    public String chargeAuthorized(@RequestParam double amount,
                                   @RequestParam String currency,
                                   @RequestParam String orderId) {
        return authorizedGateway.charge(amount, currency, orderId);
    }

    @PostMapping("/charge-unauthorized")
    public String chargeUnauthorized(@RequestParam double amount,
                                     @RequestParam String currency,
                                     @RequestParam String orderId) {
        try {
            return unauthorizedGateway.charge(amount, currency, orderId);
        } catch (SecurityException e) {
            return "BLOCKED: " + e.getMessage();
        }
    }
}