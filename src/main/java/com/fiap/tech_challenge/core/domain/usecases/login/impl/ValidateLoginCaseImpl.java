package com.fiap.tech_challenge.core.domain.usecases.login.impl;

import com.fiap.tech_challenge.core.adapters.LoginGateway;
import com.fiap.tech_challenge.core.domain.model.LoginDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.usecases.login.ValidateLoginCase;
import com.fiap.tech_challenge.core.exception.LoginFailedException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateLoginCaseImpl implements ValidateLoginCase {

    private final LoginGateway loginGateway;

    public ValidateLoginCaseImpl(LoginGateway loginGateway) {
        this.loginGateway = loginGateway;
    }

    @Override
    public Boolean run(LoginDomain loginDomain){
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
