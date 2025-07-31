package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.model.RestaurantDomain;
import com.fiap.tech_challenge.core.exception.AlreadyRegisteredException;
import com.fiap.tech_challenge.core.exception.InvalidUserTypeException;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.entity.RestaurantEntity;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserEntity;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.RestaurantMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fiap.tech_challenge.core.domain.model.PageResultDomain.buildPageable;
import static com.fiap.tech_challenge.core.domain.model.type.UserType.RESTAURANT_OWNER;

@Component
public class RestaurantRepositoryGateway implements RestaurantGateway {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final UserGateway userGateway;

    public RestaurantRepositoryGateway(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper, UserGateway userGateway) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
        this.userGateway = userGateway;
    }

    @Override
    public RestaurantDomain createRestaurant(RestaurantDomain restaurantDomain) {

        UserEntity owner = findAndValidateRestaurantOwner(restaurantDomain.getUserId());
        RestaurantEntity restaurantEntity = restaurantMapper.toEntity(restaurantDomain);

        restaurantEntity.setRestaurantOwner(owner);
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

        UserEntity userEntity = userGateway.getUserOwnerRestaurantById(userId);
        RestaurantEntity restaurantEntity = restaurantMapper.toEntity(updateRestaurantDomain);

        restaurantEntity.setRestaurantOwner(userEntity);

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

    private UserEntity findAndValidateRestaurantOwner(String userId) {
        UserEntity user = userGateway.getUserOwnerRestaurantById(userId);

        if (!RESTAURANT_OWNER.equals(user.getUserType())) {
            throw new InvalidUserTypeException("User must be a restaurant owner");
        }
        return user;
    }

}
