package com.fiap.tech_challenge.core.domain.usecases.restaurant;

import com.fiap.tech_challenge.core.domain.model.RestaurantDomain;

public interface ReadRestaurantByIdCase {

    RestaurantDomain run(String id);
}
