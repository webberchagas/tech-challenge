package com.fiap.tech_challenge.core.domain.model;

import com.fiap.tech_challenge.core.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
public class LoginDomain {
    private String email;
    private String password;
    private String confirmNewPassword;

    public void validateLoginInput() {
        if (this.getEmail() == null || this.getPassword() == null) {
            log.error("Login input validation failed - email or password is null");
            throw new NotFoundException("Email and password cannot be null");
        }
    }

    public void validateChangePasswordInput() {
        if (this.getEmail() == null || this.getPassword() == null || this.getConfirmNewPassword() == null) {
            log.error("Failure to validate password exchange - all fields should be sent: email, password and new password");
            throw new NotFoundException("Email, password and confirmation password cannot be null");
        }
    }

    public void validatePassword() {
        if (!this.getPassword().equals(this.getConfirmNewPassword())) {
            log.error("Failure in the password exchange - The passwords are different");
            throw new NotFoundException("The passwords are different");
        }
    }

}
