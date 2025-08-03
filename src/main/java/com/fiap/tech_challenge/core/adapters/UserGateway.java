package com.fiap.tech_challenge.core.adapters;

import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;


public interface UserGateway {

    void validateUserEmailIsNotAlreadyRegistered(String email);
    void validateUserDocumentNumberIsNotAlreadyRegistered(String documentNumber);
    void deleteUser(String id);
    UserDomain createUser(UserDomain user);
    UserDomain getUserById(String id);
    PageResultDomain<UserDomain> getAllUsers(Integer page, Integer size, String sort);
    UserDomain findAndValidateRestaurantOwner(String userId);


}
