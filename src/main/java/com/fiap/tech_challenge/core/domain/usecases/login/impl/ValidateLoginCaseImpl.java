package com.fiap.tech_challenge.core.domain.usecases.login.impl;

import com.fiap.tech_challenge.core.adapters.LoginGateway;

import com.fiap.tech_challenge.core.domain.model.LoginDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.usecases.login.ValidateLoginCase;
import com.fiap.tech_challenge.core.dto.login.LoginRequestDto;
import com.fiap.tech_challenge.core.exception.LoginFailedException;

import com.fiap.tech_challenge.infrastructure.persistence.mapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateLoginCaseImpl implements ValidateLoginCase {

    private final LoginGateway loginGateway;
    private final LoginMapper loginMapper;

    public ValidateLoginCaseImpl(LoginGateway loginGateway, LoginMapper loginMapper) {
        this.loginGateway = loginGateway;
        this.loginMapper = loginMapper;
    }

    @Override
    public Boolean run(LoginRequestDto request){
        var loginDomain = loginMapper.toDomainLogin(request);

        log.info("Validating request data");
        loginDomain.validateLoginInput();

        var userDomain = loginGateway.getUserByEmail(loginDomain.getEmail());
        return isValidLogin(userDomain, loginDomain);
    }

    private Boolean isValidLogin(UserDomain userDomain, LoginDomain loginDomain) {
        if (userDomain.getPassword().equals(loginDomain.getPassword())) {
            return true;
        }
        throw new LoginFailedException("Invalid email or password");
    }

}
