package com.fiap.tech_challenge.core.domain.usecases.login.impl;

import com.fiap.tech_challenge.core.adapters.LoginGateway;
import com.fiap.tech_challenge.core.domain.model.LoginDomain;
import com.fiap.tech_challenge.core.domain.usecases.login.CreatePasswordCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreatePasswordCaseImpl implements CreatePasswordCase {

    private final LoginGateway loginGateway;

    public CreatePasswordCaseImpl(LoginGateway loginGateway) {
        this.loginGateway = loginGateway;
    }

    @Override
    public void run(LoginDomain loginDomain) {
        log.info("Validating request data");
        loginDomain.validateChangePasswordInput();

        log.info("Password Verification");
        loginDomain.validatePassword();

        var userDomain = loginGateway.getUserByEmail(loginDomain.getEmail());
        userDomain.createNewPassword(loginDomain.getConfirmNewPassword());

        loginGateway.updatedPassword(userDomain);
    }

}
