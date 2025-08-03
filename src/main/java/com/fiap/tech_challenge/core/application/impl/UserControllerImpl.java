package com.fiap.tech_challenge.core.application.impl;


import com.fiap.tech_challenge.core.domain.usecases.user.*;
import com.fiap.tech_challenge.core.dto.PagedResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserCreationRequestDto;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserUpdateRequestDto;
import com.fiap.tech_challenge.core.application.UserController;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        var user = createUserCase.run(userMapper.toDomain(request));
        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto getUserById(String id) {
        var user = readUserByIdCase.run(id);
        return userMapper.toResponseDto(user);
    }

    @Override
    public PagedResponseDto<UserResponseDto> getAllUsers(final Integer page, final Integer size, final String sort) {
        var pageResult = readAllUserCase.run(page, size, sort);

        List<UserResponseDto> responseList = pageResult.getContent().stream()
                .map(userMapper::toResponseDto)
                .toList();

        return new PagedResponseDto<>(
                responseList,
                pageResult.getPage(),
                pageResult.getSize(),
                pageResult.getTotalElements()
        );
    }

    @Override
    public void deleteUserById(String id) {
        deleteUserCase.run(id);
    }

    @Override
    public UserResponseDto updateUserById(String id, UserUpdateRequestDto request) {
        var user = updateUserCase.run(id, userMapper.toDomainUpdate(request));
        return userMapper.toResponseDtoWithoutAddress(user);
    }

}
