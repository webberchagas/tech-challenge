package com.fiap.tech_challenge.core.domain.model;

import com.fiap.tech_challenge.core.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDomain {
    private String email;
    private String password;
    private String confirmNewPassword;

    public void validateLoginInput() {
        if (this.getEmail() == null || this.getPassword() == null) {
            throw new NotFoundException("Email and password cannot be null");
        }
    }

    public void validateChangePasswordInput() {
        if (this.getEmail() == null || this.getPassword() == null || this.getConfirmNewPassword() == null) {
            throw new NotFoundException("Email, password and confirmation password cannot be null");
        }
    }

    public void validatePassword() {
        if (!this.getPassword().equals(this.getConfirmNewPassword())) {
            throw new NotFoundException("The passwords are different");
        }
    }

}
