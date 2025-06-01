package com.fiap.tech_challenge.controller.impl;

import com.fiap.tech_challenge.controller.LoginController;
import com.fiap.tech_challenge.controller.dto.ChangePasswordRequestDto;
import com.fiap.tech_challenge.controller.dto.LoginRequestDto;
import com.fiap.tech_challenge.mapper.LoginMapper;
import com.fiap.tech_challenge.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/logins")
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {

    private final LoginMapper loginMapper;
    private final LoginService loginService;


    public Boolean validateLogin(LoginRequestDto request) {
        return loginService.validateLogin(loginMapper.toDomainLogin(request));
    }

    public void createPassword(ChangePasswordRequestDto request) {
        loginService.createPassword(loginMapper.toDomainPassword(request));
    }
}
