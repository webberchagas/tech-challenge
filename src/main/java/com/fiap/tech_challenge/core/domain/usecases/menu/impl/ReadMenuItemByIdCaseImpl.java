package com.fiap.tech_challenge.core.domain.usecases.menu.impl;

import com.fiap.tech_challenge.core.adapters.MenuItemGateway;
import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;
import com.fiap.tech_challenge.core.domain.usecases.menu.ReadMenuItemByIdCase;

public class ReadMenuItemByIdCaseImpl implements ReadMenuItemByIdCase {

    private final MenuItemGateway menuItemGateway;

    public ReadMenuItemByIdCaseImpl(MenuItemGateway menuItemGateway) {
        this.menuItemGateway = menuItemGateway;
    }

    @Override
    public MenuItemDomain run(String id) {
        return menuItemGateway.readMenuItem(id);
    }
}
