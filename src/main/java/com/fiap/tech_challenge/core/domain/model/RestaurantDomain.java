package com.fiap.tech_challenge.core.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RestaurantDomain {

    private String restaurantId;
    private String restaurantName;
    private String cnpj;
    private String cuisineType;
    private String openingHours;
    private AddressDomain address;
    private UserDomain user;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void createRestaurantSave() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateRestaurant(RestaurantDomain newDataRestaurantDomain) {
        this.updatedAt = LocalDateTime.now();
        this.restaurantName = newDataRestaurantDomain.getRestaurantName();
        this.cnpj = newDataRestaurantDomain.getCnpj();
        this.cuisineType = newDataRestaurantDomain.getCuisineType();
        this.openingHours= newDataRestaurantDomain.getOpeningHours();
        this.user = newDataRestaurantDomain.getUser();
    }

}
