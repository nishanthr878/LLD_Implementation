package com.lld.fooddelivery.model;

import lombok.Data;

@Data
public class DeliveryAgent {
    private String agentId;
    private String name;
    private String phone;
    private boolean available;
}
