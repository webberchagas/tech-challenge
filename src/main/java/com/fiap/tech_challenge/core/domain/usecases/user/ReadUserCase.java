package com.fiap.tech_challenge.core.domain.usecases.user;

import com.fiap.tech_challenge.core.dto.UserResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReadUserCase {
    UserResponseDto getUserById(String id);
    Page<UserResponseDto> getAllUsers(Integer page, Integer size, String sort);
}
