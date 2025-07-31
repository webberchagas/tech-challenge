package com.fiap.tech_challenge.core.domain.usecases.restaurant.impl;

import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.model.RestaurantDomain;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.ReadAllRestaurantCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadAllRestaurantCaseImpl implements ReadAllRestaurantCase {
    private final RestaurantGateway restaurantGateway;

    public ReadAllRestaurantCaseImpl(RestaurantGateway restaurantGateway) {
        this.restaurantGateway = restaurantGateway;
    }

    @Override
    public PageResultDomain<RestaurantDomain> run(final Integer page, final Integer size, final String sort) {
        log.info("Consulting all restaurants");
        return restaurantGateway.getAllRestaurants(page, size, sort);
    }

}
