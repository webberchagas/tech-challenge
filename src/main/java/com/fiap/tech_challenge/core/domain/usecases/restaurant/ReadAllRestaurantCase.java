package com.fiap.tech_challenge.core.domain.usecases.restaurant;

import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.model.RestaurantDomain;

public interface ReadAllRestaurantCase {

    PageResultDomain<RestaurantDomain> run(Integer page, Integer size, String sort);
}
