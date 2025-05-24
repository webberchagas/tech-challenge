package com.fiap.tech_challenge.service.login;

import com.fiap.tech_challenge.exceptions.NotFoundException;
import com.fiap.tech_challenge.model.UserEntity;
import com.fiap.tech_challenge.repository.UserRepository;
import com.fiap.tech_challenge.service.domain.LoginDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public Boolean validateLogin(LoginDomain loginDomain) {
        if (loginDomain.getEmail() == null || loginDomain.getPassword() == null) {
            log.error("Email or password is null");
            throw new NotFoundException("E-mail or password is null");
        }
        var userEntity = userRepository.findByEmail(loginDomain.getEmail());
        return userEntity
                .map(loginEntity -> loginEntity.getPassword().equals(loginDomain.getPassword()))
                .orElse(false);
    }

    public void createPassword(LoginDomain loginDomain) {
        validateLoginInput(loginDomain);
        var userEntity = getUserEntityByEmail(loginDomain.getEmail());
        validateOldPassword(loginDomain, userEntity);

        userEntity.createNewPassword(loginDomain.getNewPassword());
        userRepository.save(userEntity);

    }

    private void validateLoginInput(LoginDomain loginDomain) {
        if (loginDomain.getEmail() == null || loginDomain.getPassword() == null || loginDomain.getNewPassword() == null) {
            log.error("Email or passwords are null");
            throw new NotFoundException("Email and passwords cannot be null");
        }
    }

    private void validateOldPassword(LoginDomain loginDomain, UserEntity userEntity) {
        if (!userEntity.getPassword().equals(loginDomain.getPassword())) {
            log.error("Incorrect password for user with email: {}", loginDomain.getEmail());
            throw new NotFoundException("Password is incorrect");
        }
    }

    private UserEntity getUserEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User not found with email: {}", email);
                    return new NotFoundException("User not found with email: " + email);
                });
    }
}



