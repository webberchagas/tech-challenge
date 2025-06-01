package com.fiap.tech_challenge.mapper;

import com.fiap.tech_challenge.controller.dto.ChangePasswordRequestDto;
import com.fiap.tech_challenge.controller.dto.LoginRequestDto;
import com.fiap.tech_challenge.domain.LoginDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    LoginDomain toDomainLogin(LoginRequestDto loginRequestDto);

    @Mapping(target = "password",source = "newPassword")
    LoginDomain toDomainPassword(ChangePasswordRequestDto dto);
}
