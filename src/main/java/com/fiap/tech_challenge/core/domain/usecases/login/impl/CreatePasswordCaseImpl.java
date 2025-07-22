package com.fiap.tech_challenge.core.domain.usecases.login.impl;

import com.fiap.tech_challenge.core.adapters.LoginGateway;
import com.fiap.tech_challenge.core.domain.usecases.login.CreatePasswordCase;
import com.fiap.tech_challenge.core.dto.ChangePasswordRequestDto;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreatePasswordCaseImpl implements CreatePasswordCase {

    private final LoginGateway loginGateway;
    private final LoginMapper loginMapper;

    public CreatePasswordCaseImpl(LoginGateway loginGateway, LoginMapper loginMapper) {
        this.loginGateway = loginGateway;
        this.loginMapper = loginMapper;
    }

    @Override
    public void run(ChangePasswordRequestDto request) {
        var loginDomain = loginMapper.toDomainPassword(request);

        log.error("Change password validation failed - email, password and confirmation password cannot be null");
        loginDomain.validateChangePasswordInput();

        log.error("The passwords are different");
        loginDomain.validatePassword();

        var userEntity = loginGateway.getUserByEmail(loginDomain.getEmail());
        userEntity.createNewPassword(loginDomain.getConfirmNewPassword());

        loginGateway.updatedPassword(userEntity);

    }

}
