package com.fiap.tech_challenge.core.adapters;

import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.dto.user.PagedResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;


public interface UserGateway {

    void doesUserEmailExists(String email);
    void doesUserDocumentNumberExists(String documentNumber);
    UserResponseDto createUser(UserDomain user);

    void deleteUser(String id);

    UserDomain searchUserById(String id);
    UserResponseDto getUserById(String id);
    PagedResponseDto<UserResponseDto> getAllUsers(Integer page, Integer size, String sort);

}
