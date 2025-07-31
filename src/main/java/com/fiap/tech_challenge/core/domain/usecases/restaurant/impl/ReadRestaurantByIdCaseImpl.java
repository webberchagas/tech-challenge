package com.fiap.tech_challenge.core.domain.usecases.restaurant.impl;

import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.domain.model.RestaurantDomain;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.ReadRestaurantByIdCase;

public class ReadRestaurantByIdCaseImpl implements ReadRestaurantByIdCase {

    private final RestaurantGateway restaurantGateway;

    public ReadRestaurantByIdCaseImpl(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    @Override
    public RestaurantDomain run(String id) {
        return restaurantGateway.getRestaurantById(id);
    }
}
