package com.fiap.tech_challenge.service.user;

import com.fiap.tech_challenge.controller.dto.UserResponseDto;
import com.fiap.tech_challenge.domain.AddressDomain;
import com.fiap.tech_challenge.domain.UserDomain;
import com.fiap.tech_challenge.exception.AlreadyRegisteredException;
import com.fiap.tech_challenge.mapper.UserMapper;
import com.fiap.tech_challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto createUser(UserDomain user) {
        log.info("Creating user: {}", user.getEmail());
        validateUserDocumentNumber(user);
        validateUserEmail(user);

        log.info("Save user in data base: {}", user.getEmail());
        user.createUserSave();
        user.getAddress().forEach(AddressDomain::createDateAddressSave);

        var userEntity = userMapper.toEntity(user);
        userEntity.setUserIdInAddress();
        var entitySaved = userRepository.save(userEntity);
        return userMapper.toResponseDto(entitySaved);
    }

    private void validateUserDocumentNumber(UserDomain user) {
        if (user.getDocumentNumber() != null) {
            userRepository.findByDocumentNumber(user.getDocumentNumber())
                    .ifPresent(u -> {
                        throw new AlreadyRegisteredException("Document number already registered: " + user.getDocumentNumber());
                    });
        }
    }

    private void validateUserEmail(UserDomain user) {
        if (user.getEmail() != null) {
            userRepository.findByEmail(user.getEmail())
                    .ifPresent(u -> {
                        throw new AlreadyRegisteredException("Email already registered: " + user.getEmail());
                    });
        }
    }

}
