package com.fiap.tech_challenge.infrastructure.application.impl;


import com.fiap.tech_challenge.core.domain.usecases.user.*;
import com.fiap.tech_challenge.core.dto.user.UserCreationRequestDto;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserUpdateRequestDto;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.UserMapper;
import com.fiap.tech_challenge.infrastructure.application.UserController;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final CreateUserCase createUserCase;
    private final ReadAllUserCase readAllUserCase;
    private final ReadUserByIdCase readUserByIdCase;
    private final UpdateUserCase updateUserCase;
    private final DeleteUserCase deleteUserCase;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto createUser(UserCreationRequestDto request) {
        return createUserCase.run(userMapper.toDomain(request));
    }

    @Override
    public UserResponseDto getUserById(String id) {
        var userResponseDto = readUserByIdCase.run(id);
        return userResponseDto;
    }

    @Override
    public Page<UserResponseDto> getAllUsers(final Integer page, final Integer size, final String sort) {
        return readAllUserCase.run(page, size, sort);
    }

    @Override
    public void deleteUserById(String id) {
        deleteUserCase.run(id);
    }

    @Override
    public UserResponseDto updateUserById(String id, UserUpdateRequestDto request) {
      return updateUserCase.run(id, userMapper.toDomainUpdate(request));
    }

}
