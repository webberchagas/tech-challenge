package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.usecases.user.CreateUserCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateUserCaseImpl implements CreateUserCase {
    private final UserGateway userGateway;

    private CreateUserCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public static CreateUserCaseImpl create(UserGateway userGateway) {
        return new CreateUserCaseImpl(userGateway);
    }

    @Override
    public UserDomain run(UserDomain user) {
        log.info("Creating user: {}", user.getEmail());
        user.createUserSave();
        userGateway.ensureUserEmailIsNotAlreadyRegistered(user.getEmail());
        userGateway.ensureUserDocumentNumberIsNotAlreadyRegistered(user.getDocumentNumber());

        user.getAddress().forEach(AddressDomain::createDateAddressSave);

        log.info("Save user in data base: {}", user.getEmail());
        return userGateway.createUser(user);
    }
}
