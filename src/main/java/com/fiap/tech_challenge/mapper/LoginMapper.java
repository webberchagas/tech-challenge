package com.fiap.tech_challenge.mapper;

import com.fiap.tech_challenge.controller.dto.ChangePasswordRequestDto;
import com.fiap.tech_challenge.controller.dto.LoginRequestDto;
import com.fiap.tech_challenge.service.domain.LoginDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    @Mapping(target = "confirmNewPassword", ignore = true)
    LoginDomain toDomainLogin(LoginRequestDto loginRequestDto);

    @Mapping(target = "password",source = "newPassword")
    @Mapping(target = "confirmNewPassword", source = "confirmNewPassword")
    LoginDomain toDomainPassword(ChangePasswordRequestDto dto);
}
