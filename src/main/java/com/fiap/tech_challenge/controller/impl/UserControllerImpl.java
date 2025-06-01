package com.fiap.tech_challenge.controller.impl;

import com.fiap.tech_challenge.controller.UserController;
import com.fiap.tech_challenge.controller.dto.UserCreationRequestDto;
import com.fiap.tech_challenge.controller.dto.UserResponseDto;
import com.fiap.tech_challenge.controller.dto.UserUpdateRequestDto;
import com.fiap.tech_challenge.mapper.UserMapper;
import com.fiap.tech_challenge.service.user.CreateUserService;
import com.fiap.tech_challenge.service.user.DeleteUserService;
import com.fiap.tech_challenge.service.user.ReadUserService;
import com.fiap.tech_challenge.service.user.UpdateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final CreateUserService createUserService;
    private final ReadUserService readUserService;
    private final UpdateUserService updateUserService;
    private final DeleteUserService deleteUserService;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto createUser(UserCreationRequestDto request) {
        var userDate = userMapper.toDomain(request);
        return createUserService.createUser(userDate);
    }

    @Override
    public UserResponseDto getUserById(String id) {
        return readUserService.getUserById(id);
    }


    @Override
    public List<UserResponseDto> getAllUsers() {
        return readUserService.getAllUsers();
    }

    @Override
    public void deleteUserById(String id) {
        deleteUserService.deleteUserById(id);
    }

    @Override
    public UserResponseDto updateUserById(String id, UserUpdateRequestDto request) {
        var userDomain = userMapper.toDomainUpdate(request);
        return updateUserService.updateUserById(id, userDomain);
    }

}
