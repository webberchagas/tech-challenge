package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.model.RestaurantDomain;
import com.fiap.tech_challenge.core.exception.AlreadyRegisteredException;
import com.fiap.tech_challenge.core.exception.InvalidUserTypeException;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.entity.RestaurantEntity;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.RestaurantMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fiap.tech_challenge.core.domain.model.PageResultDomain.buildPageable;

@Component
public class RestaurantRepositoryGateway implements RestaurantGateway {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantRepositoryGateway(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public RestaurantDomain createRestaurant(RestaurantDomain restaurantDomain) {

        RestaurantEntity restaurantEntity = restaurantMapper.toEntity(restaurantDomain);

        return restaurantMapper.toDomain(restaurantRepository.save(restaurantEntity));
    }

    @Override
    public RestaurantDomain getRestaurantById(String id) {
        RestaurantEntity restaurant = restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant not found with ID: " + id));
        return restaurantMapper.toDomain(restaurant);
    }

    @Override
    public void deleteRestaurant(String id) {
        var restaurant = restaurantRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Restaurant not found with ID: " + id)
        );
        restaurantRepository.delete(restaurant);
    }

    @Override
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
    }

    @Override
    public void validateUserIsNotRestaurantOwner(String userId) {
        var restaurants = restaurantRepository.findByUser_UserId(userId);

        if (!restaurants.isEmpty()) {
            throw new InvalidUserTypeException(
                    "Action not allowed. This user owns " + restaurants.size() + " restaurant(s)."
            );
        }
    }

}
