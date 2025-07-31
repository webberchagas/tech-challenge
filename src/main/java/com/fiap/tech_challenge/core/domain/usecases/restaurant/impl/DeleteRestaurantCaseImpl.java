package com.fiap.tech_challenge.core.domain.usecases.restaurant.impl;

import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.DeleteRestaurantCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteRestaurantCaseImpl implements DeleteRestaurantCase {

    private final RestaurantGateway restaurantGateway;

    public DeleteRestaurantCaseImpl(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    @Override
    public void run(String id) {
        log.info("Deleting restaurant by user id: {}", id);
        this.restaurantGateway.deleteRestaurant(id);
    }
}
