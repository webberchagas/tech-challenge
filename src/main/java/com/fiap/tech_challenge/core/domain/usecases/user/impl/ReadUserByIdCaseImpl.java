package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.usecases.user.ReadUserByIdCase;

public class ReadUserByIdCaseImpl implements ReadUserByIdCase {

    private final UserGateway userGateway;

    public ReadUserByIdCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserDomain run(String id) {
        return userGateway.getUserById(id);
    }
}
