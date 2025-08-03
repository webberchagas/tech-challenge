package com.fiap.tech_challenge.core.adapters;

import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;

public interface MenuItemGateway {

    MenuItemDomain createMenuItem(MenuItemDomain menuItem);
    MenuItemDomain readMenuItem(String id);
    void deleteMenuItem(String id);

}
