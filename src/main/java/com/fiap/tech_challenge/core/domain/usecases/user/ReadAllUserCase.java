package com.fiap.tech_challenge.core.domain.usecases.user;

import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import org.springframework.data.domain.Page;

public interface ReadAllUserCase {

    Page<UserResponseDto> run(Integer page, Integer size, String sort);
}
