package com.fiap.tech_challenge.service;

import com.fiap.tech_challenge.exceptions.UserNotFoundException;
import com.fiap.tech_challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteUserService {

    private final UserRepository userRepository;

    public void deleteUserById(String id) {
        log.info("Deleting user by user id: {}", id);
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        userRepository.delete(user);
    }
}
