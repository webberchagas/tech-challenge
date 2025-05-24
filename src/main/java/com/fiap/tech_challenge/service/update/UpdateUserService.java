package com.fiap.tech_challenge.service.update;

import com.fiap.tech_challenge.exceptions.NotFoundException;
import com.fiap.tech_challenge.mapper.UserMapper;
import com.fiap.tech_challenge.model.UserEntity;
import com.fiap.tech_challenge.repository.UserRepository;
import com.fiap.tech_challenge.service.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateUserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public void updateUserById(String id, UserDomain userDomain) {
        var entityDataBase = findUserEntity(id);

        log.info("Updating user with ID: {}", entityDataBase.getUserId());
        var domainDataBase = userMapper.fromEntityToDomain(entityDataBase);
        domainDataBase.updateUser(userDomain);

        userRepository.save(userMapper.toEntity(domainDataBase));
    }


    private UserEntity findUserEntity(String id) {
        log.info("Searching for user by ID: {} for update", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }
}
