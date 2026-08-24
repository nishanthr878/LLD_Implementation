package com.lld.fooddelivery.model;

import com.lld.fooddelivery.model.enums.PaymentMethod;
import com.lld.fooddelivery.model.enums.PaymentStatus;
import lombok.Data;

@Data
public class Payment {
    private String paymentId;
    private String orderId;
    private double amount;
    private PaymentMethod method;
    private PaymentStatus status;
}
