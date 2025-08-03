package com.fiap.tech_challenge.core.domain.usecases.restaurant.impl;

import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.RestaurantDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.CreateRestaurantCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateRestaurantCaseImpl implements CreateRestaurantCase {

    private final RestaurantGateway restaurantGateway;
    private final UserGateway userGateway;

    private CreateRestaurantCaseImpl(RestaurantGateway restaurantGateway, UserGateway userGateway) {
        this.restaurantGateway = restaurantGateway;
        this.userGateway = userGateway;
    }

    public static CreateRestaurantCaseImpl create(RestaurantGateway restaurantGateway, UserGateway userGateway) {
        return new CreateRestaurantCaseImpl(restaurantGateway, userGateway);
    }

    @Override
    public RestaurantDomain run(RestaurantDomain restaurant) {
        log.info("Creating restaurant: {}", restaurant.getRestaurantName());
        restaurantGateway.ensureCnpjIsNotAlreadyRegistered(restaurant.getCnpj());
        UserDomain user = userGateway.findAndValidateRestaurantOwner(restaurant.getUserId());
        restaurant.createRestaurantSave(user);

        restaurant.getAddress().createDateAddressSave();
        return restaurantGateway.createRestaurant(restaurant);
    }

}
