package com.fiap.tech_challenge.service.read;

import com.fiap.tech_challenge.controller.dto.UserResponseDto;
import com.fiap.tech_challenge.controller.type.UserType;
import com.fiap.tech_challenge.exceptions.NotFoundException;
import com.fiap.tech_challenge.mapper.UserMapper;
import com.fiap.tech_challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public UserResponseDto getUserByEmail(String email) {
        log.info("Consulting user by e-mail: {}", email);
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with e-mail: " + email));

        return userMapper.toResponseDto(user);
    }

    public List<UserResponseDto> getUserByUserType(UserType userType) {
        log.info("Consulting user by user type: {}", userType);
        var users = userRepository.findByUserType(userType);

        if (users.isEmpty()) {
            throw new NotFoundException("No users found with user type: " + userType);
        }

        return users.stream()
                .map(userMapper::toResponseDto)
                .toList();
    }
}
