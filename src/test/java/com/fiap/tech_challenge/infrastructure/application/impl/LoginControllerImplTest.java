package com.fiap.tech_challenge.infrastructure.application.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.tech_challenge.core.domain.usecases.login.CreatePasswordCase;
import com.fiap.tech_challenge.core.domain.usecases.login.ValidateLoginCase;
import com.fiap.tech_challenge.core.dto.login.ChangePasswordRequestDto;
import com.fiap.tech_challenge.core.dto.login.LoginRequestDto;
import com.fiap.tech_challenge.core.exception.ControllerExceptionHandler;
import com.fiap.tech_challenge.core.exception.LoginFailedException;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.helper.TestHelper;
import com.fiap.tech_challenge.infrastructure.application.LoginController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginControllerImplTest {

    @Mock
    private ValidateLoginCase validateLoginCase;
    @Mock
    private CreatePasswordCase createPasswordCase;

    private MockMvc mockMvc;

    private AutoCloseable mock;

    private String emailTest;
    private String passwordTest;
    private String confirmNewPasswordTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        LoginController loginController = new LoginControllerImpl(validateLoginCase, createPasswordCase);
        mockMvc = MockMvcBuilders
                .standaloneSetup(loginController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();

        emailTest = "john@email.com";
        passwordTest = "password";
        confirmNewPasswordTest = "password";
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @DisplayName("Deve validar o login")
    @Test
    void shouldBeValidateLogin () throws Exception {
        var loginRequestDto = createLoginRequestDto();
        when(validateLoginCase.run(any())).thenReturn(true);

        mockMvc.perform(
                post("/api/v1/logins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.asJsonString(loginRequestDto))
        ).andExpect(status().isOk());

        verify(validateLoginCase, times(1)).run(any());
    }

    @DisplayName("Deve retornar status 404 quando lançar NotFoundException")
    @Test
    void shouldBeReturn404StatusWhenThrowNotFoundException () throws Exception {
        var loginRequestDto = createLoginRequestDto();
        when(validateLoginCase.run(any())).thenThrow(new NotFoundException(""));

        mockMvc.perform(
                post("/api/v1/logins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.asJsonString(loginRequestDto))
        ).andExpect(status().isNotFound());

        verify(validateLoginCase, times(1)).run(any());
    }

    @DisplayName("Deve retornar status 401 quando lançar LoginFailedException")
    @Test
    void shouldBeReturn401StatusWhenThrowLoginFailedException () throws Exception {
        var loginRequestDto = createLoginRequestDto();
        when(validateLoginCase.run(any())).thenThrow(new LoginFailedException(""));

        mockMvc.perform(
                post("/api/v1/logins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.asJsonString(loginRequestDto))
        ).andExpect(status().isUnauthorized());

        verify(validateLoginCase, times(1)).run(any());
    }

    @DisplayName("Deve alterar a senha")
    @Test
    void shouldBeChangePassword () throws Exception {
        var changePasswordRequestDto = createChangePasswordRequestDto();
        doNothing().when(createPasswordCase).run(any());

        mockMvc.perform(
                post("/api/v1/logins/changePassword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.asJsonString(changePasswordRequestDto))
        ).andExpect(status().isNoContent());

        verify(createPasswordCase, times(1)).run(any());
    }

    private LoginRequestDto createLoginRequestDto () {
        return new LoginRequestDto(
                emailTest,
                passwordTest
        );
    }

    private ChangePasswordRequestDto createChangePasswordRequestDto () {
        return new ChangePasswordRequestDto(
                emailTest,
                passwordTest,
                confirmNewPasswordTest
        );
    }

}
