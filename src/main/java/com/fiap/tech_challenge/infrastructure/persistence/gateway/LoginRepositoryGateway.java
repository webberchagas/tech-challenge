package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.LoginGateway;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.UserMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class LoginRepositoryGateway implements LoginGateway {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public LoginRepositoryGateway(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDomain getUserByEmail(String email) {
        return userMapper.fromEntityToDomain(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new NotFoundException("User not found with E-mail: " + email))
        );
    }

    @Override
    public void updatedPassword(UserDomain userEntity) {
        var user = userMapper.toEntity(userEntity);
        userRepository.save(user);
    }
}
