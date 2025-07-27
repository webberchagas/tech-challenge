package com.fiap.tech_challenge.core.domain.usecases.user;

import com.fiap.tech_challenge.core.dto.user.UserResponseDto;

public interface ReadUserByIdCase {

    UserResponseDto run(String id);
}
