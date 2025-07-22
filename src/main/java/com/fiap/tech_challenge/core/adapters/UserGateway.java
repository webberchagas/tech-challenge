package com.fiap.tech_challenge.core.adapters;

import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserGateway {

    void doesUserEmailExists(String email);
    void doesUserDocumentNumberExists(String documentNumber);
    UserResponseDto createUser(UserDomain user);

    void deleteUser(String id);

    UserDomain searchUserById(String id);
    UserResponseDto getUserById(String id);
    Page<UserResponseDto> getAllUsers(Pageable pageable);

}
