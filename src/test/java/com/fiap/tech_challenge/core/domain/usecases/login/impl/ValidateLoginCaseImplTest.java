package com.fiap.tech_challenge.core.domain.usecases.login.impl;

import com.fiap.tech_challenge.core.adapters.LoginGateway;
import com.fiap.tech_challenge.core.domain.model.LoginDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.usecases.login.ValidateLoginCase;
import com.fiap.tech_challenge.core.exception.LoginFailedException;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.LoginMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ValidateLoginCaseImplTest {

    @Mock
    private LoginGateway loginGateway;

    private ValidateLoginCase validateLoginCase;

    private AutoCloseable mock;

    private String emailTest;
    private String passwordTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        validateLoginCase = new ValidateLoginCaseImpl(loginGateway);

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
        when(loginGateway.getUserByEmail(any())).thenReturn(createUserDomain());
        var loginDomain = createLoginDomain();

        assertTrue(validateLoginCase.run(loginDomain));
        verify(loginGateway, times(1)).getUserByEmail(any());
    }

    @DisplayName("Deve lançar LoginFailedException quando realizar login com credenciais inválidas")
    @Test
    void shouldBeThrowLoginFailedExceptionWhenLoginWithInvalidCredentials () {
        when(loginGateway.getUserByEmail(any())).thenReturn(createUserDomain());
        passwordTest = "password";
        var loginDomain = createLoginDomain();

        assertThrows(LoginFailedException.class, () -> validateLoginCase.run(loginDomain));
        verify(loginGateway, times(1)).getUserByEmail(any());
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
