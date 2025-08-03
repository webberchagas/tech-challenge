package com.fiap.tech_challenge.core.domain.usecases.menu;

import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;
import com.fiap.tech_challenge.core.domain.model.PageResultDomain;

public interface ReadAllMenuItemCase {

    PageResultDomain<MenuItemDomain> run(String restaurantId, Integer page, Integer size, String sort);
}
