package com.fiap.tech_challenge.core.application.impl;

import com.fiap.tech_challenge.core.application.MenuItemController;
import com.fiap.tech_challenge.core.domain.usecases.menu.CreateMenuItemCase;
import com.fiap.tech_challenge.core.domain.usecases.menu.DeleteMenuItemCase;
import com.fiap.tech_challenge.core.domain.usecases.menu.ReadMenuItemByIdCase;
import com.fiap.tech_challenge.core.dto.PagedResponseDto;
import com.fiap.tech_challenge.core.dto.menu.MenuItemRequestDto;
import com.fiap.tech_challenge.core.dto.menu.MenuItemResponseDto;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.MenuItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuItemControllerImpl implements MenuItemController {

    private final CreateMenuItemCase createMenuItemCase;
    private final ReadMenuItemByIdCase readMenuItemByIdCase;
    private final DeleteMenuItemCase deleteMenuItemCase;
    private final MenuItemMapper menuItemMapper;

    @Override
    public MenuItemResponseDto createMenuItem(MenuItemRequestDto request) {
         var menuItem = createMenuItemCase.run(menuItemMapper.toDomain(request));
        return menuItemMapper.toResponseDto(menuItem);
    }

    @Override
    public MenuItemResponseDto getMenuItemById(String id) {
        var menuItem =  readMenuItemByIdCase.run(id);
        return menuItemMapper.toResponseDto(menuItem);
    }

    @Override
    public PagedResponseDto<MenuItemResponseDto> getAllMenuItemsByRestaurantId(final Integer page, final Integer size, final String sort) {
        return null;
    }

    @Override
    public MenuItemResponseDto updateMenuItemById(String id, MenuItemRequestDto request) {
       return null;
    }

    @Override
    public void deleteMenuItemById(String id) {
        deleteMenuItemCase.run(id);
    }
}
