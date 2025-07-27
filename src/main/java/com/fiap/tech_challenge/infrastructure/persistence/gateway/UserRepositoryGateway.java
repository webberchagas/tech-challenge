package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import com.fiap.tech_challenge.core.exception.AlreadyRegisteredException;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.UserMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class UserRepositoryGateway implements UserGateway {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void doesUserEmailExists(String email) {
        var userEntity = userRepository.findByEmail(email);
        if (userEntity.isPresent()){
            throw new AlreadyRegisteredException("Email already registered: " + email);
        }
    }

    @Override
    public void doesUserDocumentNumberExists(String documentNumber) {
        var userEntity = userRepository.findByDocumentNumber(documentNumber);
        if (userEntity.isPresent()){
           throw new AlreadyRegisteredException("Document number already registered: " + documentNumber);
        }
    }

    @Override
    public UserResponseDto createUser(UserDomain user) {
        var userEntity = userMapper.toEntity(user);
        userEntity.setUserIdInAddress();

        var newRegister = userRepository.save(userEntity);
        return userMapper.toResponseDto(newRegister);
    }

    @Override
    public void deleteUser(String id) {
        var user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found with ID: " + id)
        );
        userRepository.delete(user);
    }

    @Override
    public UserDomain searchUserById(String id) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
        return userMapper.fromEntityToDomain(user);
    }

    @Override
    public UserResponseDto getUserById(String id) {
        var userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
        return userMapper.toResponseDto(userEntity);
    }

    @Override
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toResponseDto);
    }


}
