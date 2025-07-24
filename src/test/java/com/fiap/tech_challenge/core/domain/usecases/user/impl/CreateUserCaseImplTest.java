package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.user.CreateUserCase;
import com.fiap.tech_challenge.core.dto.UserResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateUserCaseImplTest {

    @Mock
    private UserGateway userGateway;

    private CreateUserCase createUserCase;

    private AutoCloseable mock;

    private String userIdTest;
    private String nameTest;
    private String emailTest;
    private String documentNumberTest;
    private String phoneTest;
    private String passwordTest;
    private UserType userTypeTest;
    private List<AddressDomain> addressTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        createUserCase = CreateUserCaseImpl.create(userGateway);

        userIdTest = "0ea0d8bb-bc69-4977-b2f4-536c335fdae4";
        nameTest = "John";
        emailTest = "john@email.com";
        documentNumberTest = "12345678900";
        phoneTest = "99999999999";
        passwordTest = "password";
        userTypeTest = UserType.CLIENT;
        addressTest = new ArrayList<>();
        addressTest.add(new AddressDomain(
                "d3aaf5c4-9729-4c37-891a-c851874a2b62",
                "Rua Beija-flor",
                "1000",
                "",
                "São Cristóvão",
                "Itacoatiara",
                "AM",
                "Brasil",
                "69103-312",
                null,
                null,
                null
        ));
    }

    @AfterEach
    void tearDown () throws Exception {
        mock.close();
    }

    @DisplayName("Deve criar um novo usuário")
    @Test
    void shouldBeCreateNewUser () {
        var userResponseDto = createUserResponseDto();
        var userDomain = createUserDomain();
        doNothing().when(userGateway).doesUserEmailExists(any(String.class));
        doNothing().when(userGateway).doesUserDocumentNumberExists(any(String.class));
        when(userGateway.createUser(any())).thenReturn(userResponseDto);

        assertEquals(userResponseDto, createUserCase.run(userDomain));

        verify(userGateway, times(1)).doesUserEmailExists(any());
        verify(userGateway, times(1)).doesUserDocumentNumberExists(any());
    }

    private UserDomain createUserDomain () {
        return new UserDomain(
                null,
                nameTest,
                emailTest,
                documentNumberTest,
                phoneTest,
                passwordTest,
                null,
                null,
                userTypeTest,
                addressTest
        );
    }

    private UserResponseDto createUserResponseDto () {
        return new UserResponseDto(
                userIdTest,
                nameTest,
                documentNumberTest,
                emailTest,
                phoneTest,
                userTypeTest,
                null
        );
    }

}
