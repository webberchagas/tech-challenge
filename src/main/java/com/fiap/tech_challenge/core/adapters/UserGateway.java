package com.fiap.tech_challenge.core.adapters;

import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserEntity;


public interface UserGateway {

    void ensureUserEmailIsNotAlreadyRegistered(String email);
    void ensureUserDocumentNumberIsNotAlreadyRegistered(String documentNumber);
    void ensureUserIsNotRestaurantOwner(String id);
    void deleteUser(String id);
    UserDomain createUser(UserDomain user);
    UserEntity getUserOwnerRestaurantById(String id);
    UserDomain getUserById(String id);
    PageResultDomain<UserDomain> getAllUsers(Integer page, Integer size, String sort);


}
