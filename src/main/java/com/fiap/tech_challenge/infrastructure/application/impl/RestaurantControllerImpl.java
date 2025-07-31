package com.fiap.tech_challenge.infrastructure.application.impl;

import com.fiap.tech_challenge.core.domain.usecases.restaurant.*;
import com.fiap.tech_challenge.core.dto.restaurant.RestaurantRequestDto;
import com.fiap.tech_challenge.core.dto.restaurant.RestaurantResponseDto;
import com.fiap.tech_challenge.core.dto.PagedResponseDto;
import com.fiap.tech_challenge.infrastructure.application.RestaurantController;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantControllerImpl implements RestaurantController {

    private final CreateRestaurantCase createRestaurantCase;
    private final ReadRestaurantByIdCase readRestaurantByIdCase;
    private final UpdateRestaurantCase updateRestaurantCase;
    private final DeleteRestaurantCase deleteRestaurantCase;
    private final ReadAllRestaurantCase readAllRestaurantCase;
    private final RestaurantMapper restaurantMapper;

    @Override
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto request) {
         var restaurant = createRestaurantCase.run(restaurantMapper.toDomain(request));
        return restaurantMapper.toResponseDto(restaurant);
    }

    @Override
    public RestaurantResponseDto getRestaurantById(String id) {
        var restaurant =  readRestaurantByIdCase.run(id);
        return restaurantMapper.toResponseDto(restaurant);
    }

    @Override
    public PagedResponseDto<RestaurantResponseDto> getAllRestaurants(final Integer page, final Integer size, final String sort) {
        var pageResult = readAllRestaurantCase.run(page, size, sort);

        List<RestaurantResponseDto> responseList = pageResult.getContent().stream()
                .map(restaurantMapper::toResponseDto)
                .toList();

        return new PagedResponseDto<>(
                responseList,
                pageResult.getPage(),
                pageResult.getSize(),
                pageResult.getTotalElements()
        );
    }

    @Override
    public RestaurantResponseDto updateRestaurantById(String id, RestaurantRequestDto request) {
        var restaurant = updateRestaurantCase.run(id, restaurantMapper.toDomain(request));
        return restaurantMapper.toResponseDto(restaurant);
    }

    @Override
    public void deleteRestaurantById(String id) {
        deleteRestaurantCase.run(id);
    }

}
