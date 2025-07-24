package com.fiap.tech_challenge.core.domain.usecases.login.impl;

import com.fiap.tech_challenge.core.adapters.LoginGateway;
import com.fiap.tech_challenge.core.domain.model.LoginDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.usecases.login.ValidateLoginCase;
import com.fiap.tech_challenge.core.dto.LoginRequestDto;
import com.fiap.tech_challenge.core.exception.LoginFailedException;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.LoginMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateLoginCaseImplTest {

    @Mock
    private LoginGateway loginGateway;
    @Mock
    private LoginMapper loginMapper;

    private ValidateLoginCase validateLoginCase;

    private AutoCloseable mock;

    private String emailTest;
    private String passwordTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        validateLoginCase = new ValidateLoginCaseImpl(loginGateway, loginMapper);

        emailTest = "john@email.com";
        passwordTest = "123456";
    }

    @AfterEach
    void tearDown () throws Exception {
        mock.close();
    }

    @DisplayName("Deve retornar true quando realizar login com as credenciais válidas")
    @Test
    void shouldBeReturnTrueWhenLoginWithValidCredentials () {
        when(loginMapper.toDomainLogin(any())).thenReturn(createLoginDomain());
        when(loginGateway.getUserByEmail(any())).thenReturn(createUserDomain());
        var loginRequestDto = createLoginRequestDto();

        assertTrue(validateLoginCase.run(loginRequestDto));
        verify(loginMapper, times(1)).toDomainLogin(any());
        verify(loginGateway, times(1)).getUserByEmail(any());
    }

    @DisplayName("Deve lançar LoginFailedException quando realizar login com credenciais inválidas")
    @Test
    void shouldBeThrowLoginFailedExceptionWhenLoginWithInvalidCredentials () {
        when(loginGateway.getUserByEmail(any())).thenReturn(createUserDomain());
        passwordTest = "password";
        when(loginMapper.toDomainLogin(any())).thenReturn(createLoginDomain());
        var loginRequestDto = createLoginRequestDto();

        assertThrows(LoginFailedException.class, () -> validateLoginCase.run(loginRequestDto));
        verify(loginMapper, times(1)).toDomainLogin(any());
        verify(loginGateway, times(1)).getUserByEmail(any());
    }

    private LoginRequestDto createLoginRequestDto () {
        return new LoginRequestDto(
                emailTest,
                passwordTest
        );
    }

    private LoginDomain createLoginDomain () {
        return new LoginDomain(
                emailTest,
                passwordTest,
                null
        );
    }

    private UserDomain createUserDomain () {
        return new UserDomain(
                null,
                null,
                emailTest,
                null,
                null,
                passwordTest,
                null,
                null,
                null,
                null
        );
    }

}
