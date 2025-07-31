package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.usecases.user.ReadAllUserCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadAllUserCaseImpl implements ReadAllUserCase {
    private final UserGateway userGateway;

    public ReadAllUserCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public PageResultDomain<UserDomain> run(final Integer page, final Integer size, final String sort) {
        log.info("Consulting all users");
        return userGateway.getAllUsers(page, size, sort);
    }

}
