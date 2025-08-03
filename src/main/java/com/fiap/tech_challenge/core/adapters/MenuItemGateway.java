package com.fiap.tech_challenge.core.adapters;

import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;
import com.fiap.tech_challenge.core.domain.model.PageResultDomain;

public interface MenuItemGateway {

    MenuItemDomain createMenuItem(MenuItemDomain menuItem);
    MenuItemDomain readMenuItem(String id);
    PageResultDomain<MenuItemDomain> getAllMenuItemsByRestaurantId(String restaurantId, Integer page, Integer size, String sort);
    void deleteMenuItem(String id);

}
