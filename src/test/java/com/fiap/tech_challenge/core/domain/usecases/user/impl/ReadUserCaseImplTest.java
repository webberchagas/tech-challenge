package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.user.DeleteUserCase;
import com.fiap.tech_challenge.core.domain.usecases.user.ReadUserCase;
import com.fiap.tech_challenge.core.dto.UserResponseDto;
import org.apache.catalina.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReadUserCaseImplTest {

    @Mock
    private UserGateway userGateway;

    private ReadUserCase readUserCase;

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
        readUserCase = new ReadUserCaseImpl(userGateway);

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

        assertEquals(userResponseDto, readUserCase.getUserById(userIdTest));

        verify(userGateway, times(1)).getUserById(any(String.class));
    }

    @DisplayName("Deve buscar todos os usuários paginados com asc")
    @Test
    void shouldBeFindAllUsersWithPaginationWithAsc () {
        var userResponseDtoPage = createUserResponseDtoPage();
        when(userGateway.getAllUsers(any(Pageable.class))).thenReturn(userResponseDtoPage);

        assertEquals(userResponseDtoPage, readUserCase.getAllUsers(0, 10, "id,asc"));

        verify(userGateway, times(1)).getAllUsers(any(Pageable.class));
    }

    @DisplayName("Deve buscar todos os usuários paginados com desc")
    @Test
    void shouldBeFindAllUsersWithPaginationWithDesc () {
        var userResponseDtoPage = createUserResponseDtoPage();
        when(userGateway.getAllUsers(any(Pageable.class))).thenReturn(userResponseDtoPage);

        assertEquals(userResponseDtoPage, readUserCase.getAllUsers(0, 10, "id,desc"));

        verify(userGateway, times(1)).getAllUsers(any(Pageable.class));
    }

    @DisplayName("Deve buscar todos os usuários paginados sem sort")
    @Test
    void shouldBeFindAllUsersWithPaginationWithoutSort () {
        var userResponseDtoPage = createUserResponseDtoPage();
        when(userGateway.getAllUsers(any(Pageable.class))).thenReturn(userResponseDtoPage);

        assertEquals(userResponseDtoPage, readUserCase.getAllUsers(0, 10, null));

        verify(userGateway, times(1)).getAllUsers(any(Pageable.class));
    }

    @DisplayName("Deve buscar todos os usuários paginados com sort vazio")
    @Test
    void shouldBeFindAllUsersWithPaginationWithEmptySort () {
        var userResponseDtoPage = createUserResponseDtoPage();
        when(userGateway.getAllUsers(any(Pageable.class))).thenReturn(userResponseDtoPage);

        assertEquals(userResponseDtoPage, readUserCase.getAllUsers(0, 10, ""));

        verify(userGateway, times(1)).getAllUsers(any(Pageable.class));
    }

    @DisplayName("Deve buscar todos os usuários paginados sem direction no sort")
    @Test
    void shouldBeFindAllUsersWithPaginationWithoutDiretionInSort () {
        var userResponseDtoPage = createUserResponseDtoPage();
        when(userGateway.getAllUsers(any(Pageable.class))).thenReturn(userResponseDtoPage);

        assertEquals(userResponseDtoPage, readUserCase.getAllUsers(0, 10, "id"));

        verify(userGateway, times(1)).getAllUsers(any(Pageable.class));
    }

    private Page<UserResponseDto> createUserResponseDtoPage() {
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        userResponseDtoList.add(createUserResponseDto());
        userResponseDtoList.add(createUserResponseDto());
        userResponseDtoList.add(createUserResponseDto());

        return new PageImpl<>(userResponseDtoList);
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
