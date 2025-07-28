package com.fiap.tech_challenge.core.domain.usecases.login;

import com.fiap.tech_challenge.core.dto.login.ChangePasswordRequestDto;

public interface CreatePasswordCase {

    void run(ChangePasswordRequestDto request);
}
