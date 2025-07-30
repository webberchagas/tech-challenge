package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.usecases.user.ReadAllUserCase;
import com.fiap.tech_challenge.core.dto.user.PagedResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadAllUserCaseImpl implements ReadAllUserCase {
    private final UserGateway userGateway;

    public ReadAllUserCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public PagedResponseDto<UserResponseDto> run(final Integer page, final Integer size, final String sort) {
        log.info("Consulting all users");
        return userGateway.getAllUsers(page, size, sort);
    }

}
