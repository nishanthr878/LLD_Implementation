package com.lld.fooddelivery.model;

import lombok.Data;

import java.util.List;

@Data
public class Restaurant {
    private String restaurantId;
    private String name;
    private String address;
    private List<MenuItem> menu;
    private boolean open;
}
