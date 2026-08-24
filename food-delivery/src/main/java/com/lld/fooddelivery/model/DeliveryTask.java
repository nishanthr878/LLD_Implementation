package com.lld.fooddelivery.model;

import com.lld.fooddelivery.model.enums.OrderStatus;
import lombok.Data;

@Data
public class DeliveryTask {
    private String taskId;
    private Order order;
    private DeliveryAgent agent;
    private OrderStatus status;
}
