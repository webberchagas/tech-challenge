package com.fiap.tech_challenge.core.domain.usecases.login;

import com.fiap.tech_challenge.core.domain.model.LoginDomain;
import com.fiap.tech_challenge.core.dto.ChangePasswordRequestDto;
import com.fiap.tech_challenge.core.dto.LoginRequestDto;

public interface CreatePasswordCase {

    void run(ChangePasswordRequestDto request);
}
