package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.usecases.user.DeleteUserCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteUserCaseImpl implements DeleteUserCase {

    private final UserGateway userGateway;

    public DeleteUserCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void run(String id) {
        log.info("Deleting user by user id: {}", id);
        userGateway.ensureUserIsNotRestaurantOwner(id);
        this.userGateway.deleteUser(id);
    }
}
