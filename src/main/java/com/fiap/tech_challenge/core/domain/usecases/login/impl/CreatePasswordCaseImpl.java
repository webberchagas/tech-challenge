package com.fiap.tech_challenge.core.domain.usecases.login.impl;

import com.fiap.tech_challenge.core.adapters.LoginGateway;
import com.fiap.tech_challenge.core.domain.usecases.login.CreatePasswordCase;
import com.fiap.tech_challenge.core.dto.login.ChangePasswordRequestDto;
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

        log.info("Validating request data");
        loginDomain.validateChangePasswordInput();

        log.info("Password Verification");
        loginDomain.validatePassword();

        var userEntity = loginGateway.getUserByEmail(loginDomain.getEmail());
        userEntity.createNewPassword(loginDomain.getConfirmNewPassword());

        loginGateway.updatedPassword(userEntity);

    }

}
