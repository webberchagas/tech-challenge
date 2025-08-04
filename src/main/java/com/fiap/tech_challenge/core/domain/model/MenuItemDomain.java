package com.fiap.tech_challenge.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MenuItemDomain {

    private String menuItemId;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean availableInStoreOnly;
    private String photoPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String restaurantId;
    private RestaurantDomain restaurant;

    public void createMenuItem(RestaurantDomain restaurant) {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.restaurant = restaurant;
    }

    public void updateMenuItem(MenuItemDomain menuItemDomain) {
        this.updatedAt = LocalDateTime.now();
        this.name = menuItemDomain.getName();
        this.description = menuItemDomain.getDescription();
        this.price = menuItemDomain.getPrice();
        this.availableInStoreOnly = menuItemDomain.isAvailableInStoreOnly();
        this.photoPath = menuItemDomain.getPhotoPath();
    }

}
