package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.usecases.user.DeleteUserCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

class DeleteUserCaseImplTest {

    @Mock
    private UserGateway userGateway;

    @Mock
    private RestaurantGateway restaurantGateway;

    private DeleteUserCase deleteUserCase;

    private AutoCloseable mock;

    private String userIdTest;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        deleteUserCase = new DeleteUserCaseImpl(userGateway, restaurantGateway);

        userIdTest = "0ea0d8bb-bc69-4977-b2f4-536c335fdae4";
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @DisplayName("Deve remover um usuário")
    @Test
    void shouldBeRemoveAnUser() {
        doNothing().when(userGateway).deleteUser(any(String.class));

        assertDoesNotThrow(() -> deleteUserCase.run(userIdTest));

        verify(userGateway, times(1)).deleteUser(any());
    }

}
