package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.user.ReadUserByIdCase;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReadUserByIdCaseImplTest {

    @Mock
    private UserGateway userGateway;

    private ReadUserByIdCase readUserByIdCase;

    private AutoCloseable mock;

    private String userIdTest;
    private String nameTest;
    private String emailTest;
    private String documentNumberTest;
    private String phoneTest;
    private UserType userTypeTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        readUserByIdCase = new ReadUserByIdCaseImpl(userGateway);

        userIdTest = "0ea0d8bb-bc69-4977-b2f4-536c335fdae4";
        nameTest = "John";
        emailTest = "john@email.com";
        documentNumberTest = "12345678900";
        phoneTest = "99999999999";
        userTypeTest = UserType.CLIENT;
    }

    @AfterEach
    void tearDown () throws Exception {
        mock.close();
    }

    @DisplayName("Deve buscar um usuário por ID")
    @Test
    void shouldBeFindUserById () {
        var userResponseDto = createUserResponseDto();
        when(userGateway.getUserById(any(String.class))).thenReturn(userResponseDto);

        assertEquals(userResponseDto, readUserByIdCase.run(userIdTest));

        verify(userGateway, times(1)).getUserById(any(String.class));
    }

    private UserResponseDto createUserResponseDto() {
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
