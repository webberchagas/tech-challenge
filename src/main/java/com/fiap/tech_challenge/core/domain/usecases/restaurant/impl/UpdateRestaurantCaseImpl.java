package com.fiap.tech_challenge.core.domain.usecases.restaurant.impl;

import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.RestaurantDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.UpdateRestaurantCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateRestaurantCaseImpl implements UpdateRestaurantCase {

    private final RestaurantGateway restaurantGateway;
    private final UserGateway userGateway;

    public UpdateRestaurantCaseImpl(RestaurantGateway restaurantGateway, UserGateway userGateway) {
        this.restaurantGateway = restaurantGateway;
        this.userGateway = userGateway;
    }

    @Override
    public RestaurantDomain run(String id, RestaurantDomain updateRestaurant) {
        log.info("Updating restaurant with ID: {}", id);
        var savedRestaurant = restaurantGateway.getRestaurantById(id);

        UserDomain user = userGateway.findAndValidateRestaurantOwner(updateRestaurant.getUserId());
        updateRestaurant.setRestaurantOwner(user);

        if (isChangingCnpj(updateRestaurant, savedRestaurant)) {
            restaurantGateway.ensureCnpjIsNotAlreadyRegistered(updateRestaurant.getCnpj());
        }

        savedRestaurant.getAddress().updateAddress(updateRestaurant.getAddress());
        savedRestaurant.updateRestaurant(updateRestaurant);

        return restaurantGateway.updateRestaurant(updateRestaurant.getUserId(), savedRestaurant);
    }

    private boolean isChangingCnpj(RestaurantDomain newRestaurant, RestaurantDomain currentRestaurant) {
        return !newRestaurant.getCnpj().equals(currentRestaurant.getCnpj());
    }

}
