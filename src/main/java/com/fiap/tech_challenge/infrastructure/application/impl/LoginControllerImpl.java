package com.fiap.tech_challenge.infrastructure.application.impl;

import com.fiap.tech_challenge.core.domain.usecases.login.CreatePasswordCase;
import com.fiap.tech_challenge.core.domain.usecases.login.ValidateLoginCase;
import com.fiap.tech_challenge.infrastructure.application.LoginController;
import com.fiap.tech_challenge.core.dto.login.ChangePasswordRequestDto;
import com.fiap.tech_challenge.core.dto.login.LoginRequestDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/logins")
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {

    private final ValidateLoginCase validateLoginCase;
    private final CreatePasswordCase createPasswordCase;

    public Boolean validateLogin(LoginRequestDto request) {
        return validateLoginCase.run(request);
    }

    @Override
    public void createPassword(ChangePasswordRequestDto request) {
        createPasswordCase.run(request);
    }
}
