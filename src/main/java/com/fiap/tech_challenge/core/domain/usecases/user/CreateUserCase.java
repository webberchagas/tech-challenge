package com.fiap.tech_challenge.core.domain.usecases.user;


import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.dto.UserCreationRequestDto;
import com.fiap.tech_challenge.core.dto.UserResponseDto;

public interface CreateUserCase {

    UserResponseDto run(UserDomain newUser);
}
