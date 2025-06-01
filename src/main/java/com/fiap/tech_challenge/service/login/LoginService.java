package com.fiap.tech_challenge.service.login;

import com.fiap.tech_challenge.domain.LoginDomain;
import com.fiap.tech_challenge.entity.UserEntity;
import com.fiap.tech_challenge.exception.LoginFailedException;
import com.fiap.tech_challenge.exception.NotFoundException;
import com.fiap.tech_challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public Boolean validateLogin(LoginDomain loginDomain) {
        validateLoginInput(loginDomain);
        var userEntity = getUserEntityByEmail(loginDomain.getEmail());
        return isValidLogin(userEntity, loginDomain);
    }

    public void createPassword(LoginDomain loginDomain) {
        validateChangePasswordInput(loginDomain);
        validatePassword(loginDomain);
        var userEntity = getUserEntityByEmail(loginDomain.getEmail());

        userEntity.createNewPassword(loginDomain.getConfirmNewPassword());
        userRepository.save(userEntity);

    }

    private void validateLoginInput(LoginDomain loginDomain) {
        if (loginDomain.getEmail() == null || loginDomain.getPassword() == null) {
            log.error("Login input validation failed - email or password is null");
            throw new NotFoundException("Email and password cannot be null");
        }
    }

    private void validateChangePasswordInput(LoginDomain loginDomain) {
        if (loginDomain.getEmail() == null || loginDomain.getPassword() == null || loginDomain.getConfirmNewPassword() == null) {
            log.error("Change password validation failed - email, password and confirmation password cannot be null");
            throw new NotFoundException("Email, password and confirmation password cannot be null");
        }
    }

    private void validatePassword(LoginDomain loginDomain) {
        if (!loginDomain.getPassword().equals(loginDomain.getConfirmNewPassword())) {
            log.error("The passwords are different");
            throw new NotFoundException("The passwords are different");
        }
    }

    private UserEntity getUserEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User not found with email: {}", email);
                    return new NotFoundException("User not found with email: " + email);
                });
    }

    private Boolean isValidLogin(UserEntity userEntity, LoginDomain loginDomain) {
        if (userEntity.getPassword().equals(loginDomain.getPassword())) {
            return true;
        }
        throw new LoginFailedException("Invalid email or password");
    }
}



