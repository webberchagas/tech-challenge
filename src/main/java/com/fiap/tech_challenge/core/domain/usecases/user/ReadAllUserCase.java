package com.fiap.tech_challenge.core.domain.usecases.user;

import com.fiap.tech_challenge.core.dto.user.PagedResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;

public interface ReadAllUserCase {

    PagedResponseDto<UserResponseDto> run(Integer page, Integer size, String sort);
}
