package com.lld.fooddelivery.model;

import lombok.Data;

@Data
public class MenuItem {
    private String itemId;
    private String name;
    private String description;
    private String price;
    private String restaurantId;
    private boolean available;
}


