package com.fiap.tech_challenge.infrastructure.persistence.mapper;

import com.fiap.tech_challenge.core.domain.model.MenuItemDomain;
import com.fiap.tech_challenge.core.dto.menu.MenuItemRequestDto;
import com.fiap.tech_challenge.core.dto.menu.MenuItemResponseAllDto;
import com.fiap.tech_challenge.core.dto.menu.MenuItemResponseDto;
import com.fiap.tech_challenge.infrastructure.persistence.entity.MenuItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    MenuItemDomain toDomain(MenuItemRequestDto requestDto);
    MenuItemEntity toEntity(MenuItemDomain menuItemDomain);
    MenuItemResponseAllDto toResponseAllDto(MenuItemDomain menuItemDomain);

    @Mapping(target = "restaurant.address", ignore = true)
    @Mapping(target = "restaurant.user", ignore = true)
    MenuItemDomain toDomain(MenuItemEntity menuItemEntity);

    @Mapping(target = "restaurant.address", ignore = true)
    @Mapping(target = "restaurant.user", ignore = true)
    MenuItemResponseDto toResponseDto(MenuItemDomain menuItemDomain);

}
