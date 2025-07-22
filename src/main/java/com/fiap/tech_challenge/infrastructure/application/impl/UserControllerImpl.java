package com.fiap.tech_challenge.infrastructure.application.impl;


import com.fiap.tech_challenge.core.domain.usecases.user.CreateUserCase;
import com.fiap.tech_challenge.core.domain.usecases.user.DeleteUserCase;
import com.fiap.tech_challenge.core.domain.usecases.user.ReadUserCase;
import com.fiap.tech_challenge.core.domain.usecases.user.UpdateUserCase;
import com.fiap.tech_challenge.core.dto.UserCreationRequestDto;
import com.fiap.tech_challenge.core.dto.UserResponseDto;
import com.fiap.tech_challenge.core.dto.UserUpdateRequestDto;
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
    private final ReadUserCase readUserCase;
    private final UpdateUserCase updateUserCase;
    private final DeleteUserCase deleteUserCase;
    private final UserMapper userMapper;

//    public UserControllerImpl(CreateUserCase createUserCase, DeleteUserCase deleteUserCase, UpdateUserCase updateUserCase, ReadUserCase readUserCase, UserMapper userMapper) {
//        this.createUserCase = createUserCase;
//        this.deleteUserCase = deleteUserCase;
//        this.updateUserCase = updateUserCase;
//        this.readUserCase = readUserCase;
//        this.userMapper = userMapper;
//    }

    @Override
    public UserResponseDto createUser(UserCreationRequestDto request) {
        return createUserCase.run(userMapper.toDomain(request));
    }

    @Override
    public UserResponseDto getUserById(String id) {
        var userResponseDto = readUserCase.getUserById(id);
        return userResponseDto;
    }

    @Override
    public Page<UserResponseDto> getAllUsers(final Integer page, final Integer size, final String sort) {
        return readUserCase.getAllUsers(page, size, sort);
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
