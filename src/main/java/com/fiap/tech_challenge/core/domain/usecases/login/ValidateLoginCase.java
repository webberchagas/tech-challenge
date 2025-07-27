package com.fiap.tech_challenge.core.domain.usecases.login;

import com.fiap.tech_challenge.core.dto.login.LoginRequestDto;

public interface ValidateLoginCase {

    Boolean run(LoginRequestDto request);
}
