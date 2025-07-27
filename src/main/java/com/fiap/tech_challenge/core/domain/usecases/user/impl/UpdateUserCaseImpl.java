package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.usecases.user.UpdateUserCase;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateUserCaseImpl implements UpdateUserCase {

    private final UserGateway userGateway;

    public UpdateUserCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserResponseDto run(String id, UserDomain user) {
        log.info("Searching for user by ID: {} for update", id);
        var findUser = userGateway.searchUserById(id);

        log.info("Updating user with ID: {}", findUser.getUserId());
        findUser.updateUser(user);
        return userGateway.createUser(findUser);
    }
}
