package com.fiap.tech_challenge.core.domain.usecases.menu;

import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;

public interface UpdateMenuItemCase {

    MenuItemDomain run(String id, MenuItemDomain menuItemDomain);
}
