package com.fiap.tech_challenge.mapper;

import com.fiap.tech_challenge.controller.dto.ChangePasswordRequestDto;
import com.fiap.tech_challenge.controller.dto.LoginRequestDto;
import com.fiap.tech_challenge.service.domain.LoginDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    LoginDomain toDomainLogin(LoginRequestDto loginRequestDto);
    LoginDomain toDomainPassword(ChangePasswordRequestDto dto);
}
