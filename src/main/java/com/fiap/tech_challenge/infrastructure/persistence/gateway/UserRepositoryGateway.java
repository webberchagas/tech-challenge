package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.exception.AlreadyRegisteredException;
import com.fiap.tech_challenge.core.exception.InvalidUserTypeException;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserEntity;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.UserMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.RestaurantRepository;
import com.fiap.tech_challenge.infrastructure.persistence.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fiap.tech_challenge.core.domain.model.PageResultDomain.buildPageable;


@Component
public class UserRepositoryGateway implements UserGateway {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserMapper userMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserMapper userMapper, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void ensureUserEmailIsNotAlreadyRegistered(String email) {
        var userEntity = userRepository.findByEmail(email);
        if (userEntity.isPresent()) {
            throw new AlreadyRegisteredException("Email already registered: " + email);
        }
    }

    @Override
    public void ensureUserDocumentNumberIsNotAlreadyRegistered(String documentNumber) {
        var userEntity = userRepository.findByDocumentNumber(documentNumber);
        if (userEntity.isPresent()) {
            throw new AlreadyRegisteredException("Document number already registered: " + documentNumber);
        }
    }

    @Override
    public UserDomain createUser(UserDomain user) {
        var userEntity = userMapper.toEntity(user);
        userEntity.setUserIdInAddress();

        var newRegister = userRepository.save(userEntity);
        return userMapper.toDomain(newRegister);
    }

    @Override
    public void deleteUser(String id) {
        var user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not delete. ID: " + id + " not found with")
        );
        userRepository.delete(user);
    }

    @Override
    public UserDomain getUserById(String id) {
        var userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
        return userMapper.toDomain(userEntity);
    }

    @Override
    public PageResultDomain<UserDomain> getAllUsers(Integer page, Integer size, String sort) {
        Pageable pageable = buildPageable(page, size, sort);
        Page<UserEntity> userPage = userRepository.findAll(pageable);

        List<UserDomain> content = userPage.map(userMapper::toDomain).getContent();

        return new PageResultDomain<>(
                content,
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements()
        );
    }

    @Override
    public UserEntity getUserOwnerRestaurantById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    @Override
    public void ensureUserIsNotRestaurantOwner(String userId) {
        var restaurants = restaurantRepository.findByUser_UserId(userId);

        if (!restaurants.isEmpty()) {
            throw new InvalidUserTypeException(
                    "Action not allowed. This user owns " + restaurants.size() + " restaurant(s)."
            );
        }
    }

}
