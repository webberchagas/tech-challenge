package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.usecases.user.ReadUserByIdCase;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;

public class ReadUserByIdCaseImpl implements ReadUserByIdCase {

    private final UserGateway userGateway;

    public ReadUserByIdCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserResponseDto run(String id) {
        return userGateway.getUserById(id);
    }
}
