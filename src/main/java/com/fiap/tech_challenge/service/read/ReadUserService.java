package com.fiap.tech_challenge.service.read;

import com.fiap.tech_challenge.controller.dto.UserResponseDto;
import com.fiap.tech_challenge.controller.type.UserType;
import com.fiap.tech_challenge.exceptions.NotFoundException;
import com.fiap.tech_challenge.mapper.UserMapper;
import com.fiap.tech_challenge.model.UserEntity;
import com.fiap.tech_challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto getUserById(String userId) {
        log.info("Consulting user by ID: {}", userId);
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

        return userMapper.toResponseDto(user);
    }

    public List<UserResponseDto> getAllUsers() {
        log.info("Consulting all users: ");
        List<UserEntity> users = userRepository.findAll();

        return users.stream()
                .map(user -> userMapper.toResponseDto(user))
                .collect(Collectors.toList());
    }
}
