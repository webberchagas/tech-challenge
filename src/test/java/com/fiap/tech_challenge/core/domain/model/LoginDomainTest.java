package com.fiap.tech_challenge.core.domain.model;

import com.fiap.tech_challenge.core.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDomainTest {

    private String emailTest;
    private String passwordTest;
    private String confirmNewPasswordTest;

    @BeforeEach
    void setup () {
        emailTest = "john@email.com";
        passwordTest = "123456";
        confirmNewPasswordTest = "123456";
    }

    @DisplayName("Deve criar um objeto de LoginDomain")
    @Test
    void shouldBeCreateLoginDomainObject () {
        var loginDomain = createLoginDomain();

        assertEquals(emailTest, loginDomain.getEmail());
        assertEquals(passwordTest, loginDomain.getPassword());
        assertEquals(confirmNewPasswordTest, loginDomain.getConfirmNewPassword());
    }

    @DisplayName("Deve passar a validação quando validar o login com os dados corretos")
    @Test
    void shouldBePassValidationWhenValidateLoginWithCorrectData () {
        var loginDomain = createLoginDomain();

        assertDoesNotThrow(loginDomain::validateLoginInput);
    }

    @DisplayName("Deve passar a validação quando validar troca de senha com os dados corretos")
    @Test
    void shouldBePassValidationWhenValidateChangePasswordWithCorrectData () {
        var loginDomain = createLoginDomain();

        assertDoesNotThrow(loginDomain::validateChangePasswordInput);
    }

    @DisplayName("Deve passar a validação quando validar senha com os dados corretos")
    @Test
    void shouldBePassValidationWhenValidatePasswordWithCorrectData () {
        var loginDomain = createLoginDomain();

        assertDoesNotThrow(loginDomain::validatePassword);
    }

    @DisplayName("Deve soltar NotFoundException quando validar o login sem e-mail e sem senha")
    @Test
    void shouldBeThrowsNotFoundExceptionWhenValidateObjectWithoutEmailAndPassword () {
        emailTest = null;
        passwordTest = null;
        var loginDomain = createLoginDomain();

        assertThrows(NotFoundException.class, loginDomain::validateLoginInput);
    }

    @DisplayName("Deve soltar NotFoundException quando validar a troca de senha sem e-mail, senha e confirmar senha")
    @Test
    void shouldBeThrowsNotFoundExceptionWhenValidateObjectWithoutEmailAndPasswordAndConfirmNewPassword () {
        emailTest = null;
        passwordTest = null;
        confirmNewPasswordTest = null;
        var loginDomain = createLoginDomain();

        assertThrows(NotFoundException.class, loginDomain::validateChangePasswordInput);
    }

    @DisplayName("Deve soltar NotFoundException quando validar a senha e forem diferentes")
    @Test
    void shouldBeThrowsNotFoundExceptionWhenValidateObjectWithPasswordAndConfirmNewPasswordIsDifferent () {
        confirmNewPasswordTest = "123";
        var loginDomain = createLoginDomain();

        assertThrows(NotFoundException.class, loginDomain::validatePassword);
    }

    private LoginDomain createLoginDomain () {
        return new LoginDomain(emailTest, passwordTest, confirmNewPasswordTest);
    }

}
