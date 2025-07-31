package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.user.CreateUserCase;
import com.fiap.tech_challenge.core.domain.usecases.user.DeleteUserCase;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DeleteUserCaseImplTest {

    @Mock
    private UserGateway userGateway;

    private DeleteUserCase deleteUserCase;

    private AutoCloseable mock;

    private String userIdTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        deleteUserCase = new DeleteUserCaseImpl(userGateway);

        userIdTest = "0ea0d8bb-bc69-4977-b2f4-536c335fdae4";
    }

    @AfterEach
    void tearDown () throws Exception {
        mock.close();
    }

    @DisplayName("Deve remover um usuário")
    @Test
    void shouldBeRemoveAnUser () {
        doNothing().when(userGateway).deleteUser(any(String.class));

        assertDoesNotThrow(() -> deleteUserCase.run(userIdTest));

        verify(userGateway, times(1)).deleteUser(any());
    }

}
