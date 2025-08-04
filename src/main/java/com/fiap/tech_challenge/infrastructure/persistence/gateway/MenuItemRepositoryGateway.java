package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.MenuItemGateway;
import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;
import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.entity.MenuItemEntity;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.MenuItemMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.MenuItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fiap.tech_challenge.core.domain.model.PageResultDomain.buildPageable;

@Component
public class MenuItemRepositoryGateway implements MenuItemGateway {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuItemRepositoryGateway(MenuItemRepository menuItemRepository, MenuItemMapper menuItemMapper) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;

    }

    @Override
    public MenuItemDomain createMenuItem(MenuItemDomain menuItemDomain) {
        MenuItemEntity menuItemEntity = menuItemMapper.toEntity(menuItemDomain);
        MenuItemEntity menu = menuItemRepository.save(menuItemEntity);
        return menuItemMapper.toDomain(menu);
    }

    @Override
    public MenuItemDomain readMenuItem(String id) {
        MenuItemEntity menuItem = menuItemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Menu item not found with ID: " + id));
        return menuItemMapper.toDomain(menuItem);
    }

    @Override
    public PageResultDomain<MenuItemDomain> getAllMenuItemsByRestaurantId(String restaurantId, Integer page, Integer size, String sort) {

        if (!menuItemRepository.existsByRestaurant_RestaurantId(restaurantId)) {
            throw new NotFoundException("Restaurant ID " + restaurantId + " not found.");
        }

        Pageable pageable = buildPageable(page, size, sort);
        Page<MenuItemEntity> restaurantPage = menuItemRepository.findAllByRestaurantRestaurantId(restaurantId, pageable);

        List<MenuItemDomain> content = restaurantPage.map(menuItemMapper::toDomain).getContent();

        return new PageResultDomain<>(
                content,
                restaurantPage.getNumber(),
                restaurantPage.getSize(),
                restaurantPage.getTotalElements()
        );
    }

    @Override
    public void deleteMenuItem(String id) {
        var menuItem = menuItemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Menu item not found with ID: " + id)
        );
        menuItemRepository.delete(menuItem);
    }

    @Override
    public MenuItemDomain updateMenuItem(MenuItemDomain menuItemDomain) {
        MenuItemEntity restaurantEntity = menuItemMapper.toEntity(menuItemDomain);
        return menuItemMapper.toDomain(menuItemRepository.save(restaurantEntity));
    }

}
