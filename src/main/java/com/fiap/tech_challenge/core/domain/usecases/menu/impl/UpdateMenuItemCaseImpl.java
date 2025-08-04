package com.fiap.tech_challenge.core.domain.usecases.menu.impl;

import com.fiap.tech_challenge.core.adapters.MenuItemGateway;
import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;
import com.fiap.tech_challenge.core.domain.usecases.menu.UpdateMenuItemCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateMenuItemCaseImpl implements UpdateMenuItemCase {

    private final MenuItemGateway menuItemGateway;

    public UpdateMenuItemCaseImpl(MenuItemGateway menuItemGateway) {
        this.menuItemGateway = menuItemGateway;
    }

    @Override
    public MenuItemDomain run(String id, MenuItemDomain updateMenuItem) {
        log.info("Updating menu item with ID: {}", id);
        var saveMenuItem = menuItemGateway.readMenuItem(id);

        saveMenuItem.updateMenuItem(updateMenuItem);

        return menuItemGateway.updateMenuItem(saveMenuItem);
    }

}
