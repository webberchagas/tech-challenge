package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.user.UpdateUserCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UpdateUserCaseImplTest {

    @Mock
    private UserGateway userGateway;

    @Mock
    private RestaurantGateway restaurantGateway;

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
        updateUserCase = new UpdateUserCaseImpl(userGateway, restaurantGateway);

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
        var userDomain = createUserDomain();
        when(userGateway.getUserById(any(String.class))).thenReturn(userDomain);
        when(userGateway.createUser(any(UserDomain.class))).thenReturn(userDomain);

        assertEquals(userDomain, updateUserCase.run(userIdTest, userDomain));

        verify(userGateway, times(1)).getUserById(any(String.class));
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

}
