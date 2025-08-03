package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.MenuItemGateway;
import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.entity.MenuItemEntity;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.MenuItemMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.MenuItemRepository;
import org.springframework.stereotype.Component;

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
        MenuItemEntity menuItem = menuItemRepository.findById(id).orElseThrow(() -> new NotFoundException("Menu item not found with ID: " + id));
        return menuItemMapper.toDomain(menuItem);
    }

    @Override
    public void deleteMenuItem(String id) {
        var menuItem = menuItemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Menu item not found with ID: " + id)
        );
        menuItemRepository.delete(menuItem);
    }

   /*@Override
    public RestaurantDomain updateRestaurant(String userId, RestaurantDomain updateRestaurantDomain) {

        RestaurantEntity restaurantEntity = restaurantMapper.toEntity(updateRestaurantDomain);

        return restaurantMapper.toDomain(restaurantRepository.save(restaurantEntity));
    }

    @Override
    public void ensureCnpjIsNotAlreadyRegistered(String cnpj) {
        var restaurantEntity = restaurantRepository.findByCnpj(cnpj);
        if (restaurantEntity.isPresent()) {
            throw new AlreadyRegisteredException("CNPJ already registered: " + cnpj);
        }
    }

    @Override
    public PageResultDomain<RestaurantDomain> getAllRestaurants(Integer page, Integer size, String sort) {
        Pageable pageable = buildPageable(page, size, sort);
        Page<RestaurantEntity> restaurantPage = restaurantRepository.findAll(pageable);

        List<RestaurantDomain> content = restaurantPage.map(restaurantMapper::toDomain).getContent();

        return new PageResultDomain<>(
                content,
                restaurantPage.getNumber(),
                restaurantPage.getSize(),
                restaurantPage.getTotalElements()
        );
    }*/

}
