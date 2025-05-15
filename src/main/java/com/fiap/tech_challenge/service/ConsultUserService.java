package com.fiap.tech_challenge.service;

import com.fiap.tech_challenge.controller.dto.UserResponseDto;
import com.fiap.tech_challenge.controller.type.UserType;
import com.fiap.tech_challenge.exceptions.UserNotFoundException;
import com.fiap.tech_challenge.repository.UserRepository;
import com.fiap.tech_challenge.repository.mapper.UserMapperEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsultUserService {

    private final UserRepository userRepository;
    private final UserMapperEntity userMapperEntity;

    public UserResponseDto getUserById(String userId) {
        log.info("Consulting user by ID: {}", userId);
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        return userMapperEntity.toResponseDto(user);
    }

    public UserResponseDto getUserByEmail(String email) {
        log.info("Consulting user by e-mail: {}", email);
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with e-mail: " + email));

        return userMapperEntity.toResponseDto(user);
    }

    public List<UserResponseDto> getUserByUserType(UserType userType) {
        log.info("Consulting user by user type: {}", userType);
        var users = userRepository.findByUserType(userType);

        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found with user type: " + userType);
        }

        return users.stream()
                .map(userMapperEntity::toResponseDto)
                .toList();
    }
}
