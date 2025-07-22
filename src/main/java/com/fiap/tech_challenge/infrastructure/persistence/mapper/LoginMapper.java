package com.fiap.tech_challenge.infrastructure.persistence.mapper;



import com.fiap.tech_challenge.core.domain.model.LoginDomain;
import com.fiap.tech_challenge.core.dto.ChangePasswordRequestDto;
import com.fiap.tech_challenge.core.dto.LoginRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    LoginDomain toDomainLogin(LoginRequestDto loginRequestDto);

    @Mapping(target = "password", source = "newPassword")
    LoginDomain toDomainPassword(ChangePasswordRequestDto dto);
}
