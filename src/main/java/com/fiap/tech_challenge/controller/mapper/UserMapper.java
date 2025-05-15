package com.fiap.tech_challenge.controller.mapper;

import com.fiap.tech_challenge.controller.dto.UserRequestDto;
import com.fiap.tech_challenge.service.domain.UserDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDomain toDomain(UserRequestDto userDto);
}
