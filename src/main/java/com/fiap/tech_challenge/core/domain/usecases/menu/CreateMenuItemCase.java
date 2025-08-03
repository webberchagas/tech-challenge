package com.fiap.tech_challenge.core.domain.usecases.menu;

import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;

public interface CreateMenuItemCase {

    MenuItemDomain run(MenuItemDomain newMenuItem);
}
