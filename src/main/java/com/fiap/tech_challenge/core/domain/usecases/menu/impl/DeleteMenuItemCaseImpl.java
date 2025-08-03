package com.fiap.tech_challenge.core.domain.usecases.menu.impl;

import com.fiap.tech_challenge.core.adapters.MenuItemGateway;
import com.fiap.tech_challenge.core.domain.usecases.menu.DeleteMenuItemCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteMenuItemCaseImpl implements DeleteMenuItemCase {

    private final MenuItemGateway menuItemGateway;

    public DeleteMenuItemCaseImpl(MenuItemGateway menuItemGateway) {
        this.menuItemGateway = menuItemGateway;
    }

    @Override
    public void run(String id) {
        log.info("Deleting menu item by id: {}", id);
        this.menuItemGateway.deleteMenuItem(id);
    }
}
