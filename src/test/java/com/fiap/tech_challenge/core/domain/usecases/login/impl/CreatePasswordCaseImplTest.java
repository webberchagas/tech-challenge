package com.fiap.tech_challenge.core.domain.usecases.login.impl;

import com.fiap.tech_challenge.core.adapters.LoginGateway;
import com.fiap.tech_challenge.core.domain.model.LoginDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.usecases.login.CreatePasswordCase;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.LoginMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreatePasswordCaseImplTest {

    @Mock
    private LoginGateway loginGateway;

    private AutoCloseable mock;

    private CreatePasswordCase createPasswordCase;

    private String emailTest;
    private String oldPasswordTest;
    private String newPasswordTest;
    private String confirmNewPasswordTest;

    @BeforeEach
    void setup () {
        emailTest = "john@email.com";
        oldPasswordTest = "password";
        newPasswordTest = "123456";
        confirmNewPasswordTest = "123456";

        mock = MockitoAnnotations.openMocks(this);
        createPasswordCase = new CreatePasswordCaseImpl(loginGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @DisplayName("Deve trocar senha com sucesso")
    @Test
    void shouldBeChangePasswordSuccesfully () {
        var userDomain = createUserDomain();
        var loginDomain = createLoginDomain();
        when(loginGateway.getUserByEmail(any(String.class)))
                .thenReturn(userDomain);
        doNothing().when(loginGateway).updatedPassword(any());

        createPasswordCase.run(loginDomain);

        assertEquals(newPasswordTest, userDomain.getPassword());
        verify(loginGateway, times(1))
                .getUserByEmail(any());
        verify(loginGateway, times(1))
                .updatedPassword(any());
    }

    private LoginDomain createLoginDomain() {
        return new LoginDomain(
                emailTest,
                newPasswordTest,
                confirmNewPasswordTest
        );
    }

    private UserDomain createUserDomain () {
        return new UserDomain(
                null,
                null,
                emailTest,
                null,
                null,
                oldPasswordTest,
                null,
                null,
                null,
                null
        );
    }

}
