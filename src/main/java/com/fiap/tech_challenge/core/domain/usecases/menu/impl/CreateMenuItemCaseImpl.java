package com.fiap.tech_challenge.core.domain.usecases.menu.impl;

import com.fiap.tech_challenge.core.adapters.MenuItemGateway;
import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;
import com.fiap.tech_challenge.core.domain.usecases.menu.CreateMenuItemCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateMenuItemCaseImpl implements CreateMenuItemCase {

    private final MenuItemGateway menuItemGateway;
    private final RestaurantGateway restaurantGateway;

    private CreateMenuItemCaseImpl(MenuItemGateway menuItemGateway, RestaurantGateway restaurantGateway) {
        this.menuItemGateway = menuItemGateway;
        this.restaurantGateway = restaurantGateway;
    }

    public static CreateMenuItemCaseImpl create(MenuItemGateway menuItemGateway, RestaurantGateway restaurantGateway) {
        return new CreateMenuItemCaseImpl(menuItemGateway, restaurantGateway);
    }

    @Override
    public MenuItemDomain run(MenuItemDomain menuItem) {
        log.info("Creating menu item: {} for restaurant: {}", menuItem.getName(), menuItem.getRestaurantId());
        var restaurant = restaurantGateway.getRestaurantById(menuItem.getRestaurantId());
        menuItem.createMenuItem(restaurant);
        return menuItemGateway.createMenuItem(menuItem);
    }


}
