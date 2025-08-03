package com.fiap.tech_challenge.core.domain.usecases.menu.impl;

import com.fiap.tech_challenge.core.adapters.MenuItemGateway;
import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;
import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.usecases.menu.ReadAllMenuItemCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadAllMenuItemCaseImpl implements ReadAllMenuItemCase {
    private final MenuItemGateway menuItemGateway;

    public ReadAllMenuItemCaseImpl(MenuItemGateway menuItemGateway) {
        this.menuItemGateway = menuItemGateway;
    }

    @Override
    public PageResultDomain<MenuItemDomain> run(String restaurantId, final Integer page, final Integer size, final String sort) {
        log.info("Consulting all menu items for restaurant id");
        return menuItemGateway.getAllMenuItemsByRestaurantId(restaurantId, page, size, sort);
    }

}
