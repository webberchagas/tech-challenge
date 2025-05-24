package com.fiap.tech_challenge.mapper;

import com.fiap.tech_challenge.controller.dto.ChangePasswordRequestDto;
import com.fiap.tech_challenge.controller.dto.LoginRequestDto;
import com.fiap.tech_challenge.service.domain.LoginDomain;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-23T22:01:38-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Arch Linux)"
)
@Component
public class LoginMapperImpl implements LoginMapper {

    @Override
    public LoginDomain toDomainLogin(LoginRequestDto loginRequestDto) {
        if ( loginRequestDto == null ) {
            return null;
        }

        String email = null;
        String password = null;

        email = loginRequestDto.getEmail();
        password = loginRequestDto.getPassword();

        String newPassword = null;

        LoginDomain loginDomain = new LoginDomain( email, password, newPassword );

        return loginDomain;
    }

    @Override
    public LoginDomain toDomainPassword(ChangePasswordRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        String email = null;
        String password = null;
        String newPassword = null;

        email = dto.getEmail();
        password = dto.getPassword();
        newPassword = dto.getNewPassword();

        LoginDomain loginDomain = new LoginDomain( email, password, newPassword );

        return loginDomain;
    }
}
