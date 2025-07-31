package com.fiap.tech_challenge.core.domain.usecases.restaurant.impl;

import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.domain.model.RestaurantDomain;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.CreateRestaurantCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateRestaurantCaseImpl implements CreateRestaurantCase {

    private final RestaurantGateway restaurantGateway;

    private CreateRestaurantCaseImpl(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    public static CreateRestaurantCaseImpl create(RestaurantGateway restaurantGateway) {
        return new CreateRestaurantCaseImpl(restaurantGateway);
    }

    @Override
    public RestaurantDomain run(RestaurantDomain restaurant) {
        log.info("Creating restaurant: {}", restaurant.getRestaurantName());
        restaurantGateway.ensureCnpjIsNotAlreadyRegistered(restaurant.getCnpj());
        restaurant.createRestaurantSave();

        restaurant.getAddress().createDateAddressSave();
        return restaurantGateway.createRestaurant(restaurant);
    }

}
