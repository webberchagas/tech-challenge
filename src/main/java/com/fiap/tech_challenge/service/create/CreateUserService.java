package com.fiap.tech_challenge.service.create;

import com.fiap.tech_challenge.exceptions.UserAlreadyRegisteredException;
import com.fiap.tech_challenge.mapper.UserMapper;
import com.fiap.tech_challenge.repository.UserRepository;
import com.fiap.tech_challenge.service.domain.AddressDomain;
import com.fiap.tech_challenge.service.domain.UserDomain;
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
        return userMapper.toResponseDto(userRepository.save(userEntity));
    }

    private void validateUserDocumentNumber(UserDomain user) {
        if (user.getDocumentNumber() != null) {
            userRepository.findByDocumentNumber(user.getDocumentNumber())
                    .ifPresent(u -> {
                        throw new UserAlreadyRegisteredException("Document number already registered: " + user.getDocumentNumber());
                    });
        }
    }

    private void validateUserEmail(UserDomain user) {
        if (user.getEmail() != null) {
            userRepository.findByEmail(user.getEmail())
                    .ifPresent(u -> {
                        throw new UserAlreadyRegisteredException("Email already registered: " + user.getEmail());
                    });
        }
    }

}
