package com.fiap.tech_challenge.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDomain {
    private String email;
    private String password;
    private String confirmNewPassword;

}
