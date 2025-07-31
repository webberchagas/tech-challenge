package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.user.UpdateUserCase;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UpdateUserCaseImplTest {

    @Mock
    private UserGateway userGateway;

    private UpdateUserCase updateUserCase;

    private AutoCloseable mock;

    private String userIdTest;
    private String nameTest;
    private String emailTest;
    private String documentNumberTest;
    private String phoneTest;
    private String passwordTest;
    private LocalDateTime createdAtTest;
    private LocalDateTime updatedAtTest;
    private UserType userTypeTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        updateUserCase = new UpdateUserCaseImpl(userGateway);

        userIdTest = "0ea0d8bb-bc69-4977-b2f4-536c335fdae4";
        nameTest = "John";
        emailTest = "john@email.com";
        documentNumberTest = "12345678900";
        phoneTest = "99999999999";
        passwordTest = "123456";
        createdAtTest = LocalDateTime.now();
        updatedAtTest = LocalDateTime.now();
        userTypeTest = UserType.CLIENT;
    }

    @AfterEach
    void tearDown () throws Exception {
        mock.close();
    }

    @DisplayName("Deve buscar um usuário por ID")
    @Test
    void shouldBeUpdateUser () {
        var userResponseDto = createUserResponseDto();
        var userDomain = createUserDomain();
        when(userGateway.searchUserById(any(String.class))).thenReturn(userDomain);
        when(userGateway.createUser(any(UserDomain.class))).thenReturn(userResponseDto);

        assertEquals(userResponseDto, updateUserCase.run(userIdTest, userDomain));

        verify(userGateway, times(1)).searchUserById(any(String.class));
        verify(userGateway, times(1)).createUser(any(UserDomain.class));
    }

    private UserDomain createUserDomain () {
        return new UserDomain(
                userIdTest,
                nameTest,
                emailTest,
                documentNumberTest,
                phoneTest,
                passwordTest,
                createdAtTest,
                updatedAtTest,
                userTypeTest,
                null
        );
    }

    private UserResponseDto createUserResponseDto () {
        return new UserResponseDto(
                userIdTest,
                nameTest,
                emailTest,
                documentNumberTest,
                phoneTest,
                userTypeTest,
                null
        );
    }

}
