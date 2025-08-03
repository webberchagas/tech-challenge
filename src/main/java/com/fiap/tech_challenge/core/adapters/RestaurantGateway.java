package com.fiap.tech_challenge.core.adapters;

import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.model.RestaurantDomain;

public interface RestaurantGateway {

    RestaurantDomain createRestaurant(RestaurantDomain restaurant);
    RestaurantDomain getRestaurantById(String id);
    RestaurantDomain updateRestaurant(String userId, RestaurantDomain updateRestaurantDomain);
    PageResultDomain<RestaurantDomain> getAllRestaurants(Integer page, Integer size, String sort);
    void ensureCnpjIsNotAlreadyRegistered(String cnpj);
    void deleteRestaurant(String id);
    void validateUserIsNotRestaurantOwner(String userId);
}
