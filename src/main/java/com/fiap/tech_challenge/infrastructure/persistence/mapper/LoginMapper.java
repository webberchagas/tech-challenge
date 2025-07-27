package com.fiap.tech_challenge.infrastructure.persistence.mapper;



import com.fiap.tech_challenge.core.domain.model.LoginDomain;
import com.fiap.tech_challenge.core.dto.login.ChangePasswordRequestDto;
import com.fiap.tech_challenge.core.dto.login.LoginRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    LoginDomain toDomainLogin(LoginRequestDto loginRequestDto);

    @Mapping(target = "password", source = "newPassword")
    LoginDomain toDomainPassword(ChangePasswordRequestDto dto);
}
