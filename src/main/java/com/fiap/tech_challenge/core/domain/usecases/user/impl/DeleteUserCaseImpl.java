package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.usecases.user.DeleteUserCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteUserCaseImpl implements DeleteUserCase {

    private final UserGateway userGateway;
    private final RestaurantGateway restaurantGateway;

    public DeleteUserCaseImpl(UserGateway userGateway, RestaurantGateway restaurantGateway) {
        this.userGateway = userGateway;
        this.restaurantGateway = restaurantGateway;
    }

    @Override
    public void run(String id) {
        log.info("Deleting user by user id: {}", id);
        restaurantGateway.validateUserIsNotRestaurantOwner(id);
        this.userGateway.deleteUser(id);
    }
}
