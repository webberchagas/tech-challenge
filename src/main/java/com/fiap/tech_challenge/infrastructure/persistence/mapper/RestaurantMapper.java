package com.fiap.tech_challenge.infrastructure.persistence.mapper;

import com.fiap.tech_challenge.core.domain.model.RestaurantDomain;
import com.fiap.tech_challenge.core.dto.restaurant.RestaurantRequestDto;
import com.fiap.tech_challenge.core.dto.restaurant.RestaurantResponseDto;
import com.fiap.tech_challenge.infrastructure.persistence.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDomain toDomain(RestaurantRequestDto restaurantRequestDto);
    RestaurantEntity toEntity(RestaurantDomain restaurantDomain);
    RestaurantResponseDto toResponseDto(RestaurantDomain restaurantDomain);

    @Mapping(target = "user.address", ignore = true)
    RestaurantDomain toDomain(RestaurantEntity restaurantEntity);
}
