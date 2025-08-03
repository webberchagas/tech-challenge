package com.fiap.tech_challenge.core.domain.usecases.menu;

import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;

public interface ReadMenuItemByIdCase {

    MenuItemDomain run(String id);
}
