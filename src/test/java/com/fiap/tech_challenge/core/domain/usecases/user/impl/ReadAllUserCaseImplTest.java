package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.user.ReadAllUserCase;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReadAllUserCaseImplTest {

    @Mock
    private UserGateway userGateway;

    private ReadAllUserCase readAllUserCase;

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
        readAllUserCase = new ReadAllUserCaseImpl(userGateway);

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

    @DisplayName("Deve buscar todos os usuários paginados")
    @Test
    void shouldBeGetAllUsersPaginated () {
        var userResponseDtoPage = createUserResponseDtoPage();
        when(userGateway.getAllUsers(any(Pageable.class))).thenReturn(userResponseDtoPage);

        assertEquals(userResponseDtoPage, readAllUserCase.run(0, 10, "name,asc"));

        verify(userGateway, times(1)).getAllUsers(any(Pageable.class));
    }

    @DisplayName("Deve buscar todos os usuários paginados sem sort")
    @Test
    void shouldBeGetAllUsersPaginatedWithoutSort () {
        var userResponseDtoPage = createUserResponseDtoPage();
        when(userGateway.getAllUsers(any(Pageable.class))).thenReturn(userResponseDtoPage);

        assertEquals(userResponseDtoPage, readAllUserCase.run(0, 10, null));

        verify(userGateway, times(1)).getAllUsers(any(Pageable.class));
    }

    @DisplayName("Deve buscar todos os usuários paginados com sort vazio")
    @Test
    void shouldBeGetAllUsersPaginatedWithEmptySort () {
        var userResponseDtoPage = createUserResponseDtoPage();
        when(userGateway.getAllUsers(any(Pageable.class))).thenReturn(userResponseDtoPage);

        assertEquals(userResponseDtoPage, readAllUserCase.run(0, 10, ""));

        verify(userGateway, times(1)).getAllUsers(any(Pageable.class));
    }

    @DisplayName("Deve buscar todos os usuários paginados com sort sem direction")
    @Test
    void shouldBeGetAllUsersPaginatedWithSortWithoutDirection () {
        var userResponseDtoPage = createUserResponseDtoPage();
        when(userGateway.getAllUsers(any(Pageable.class))).thenReturn(userResponseDtoPage);

        assertEquals(userResponseDtoPage, readAllUserCase.run(0, 10, "name"));

        verify(userGateway, times(1)).getAllUsers(any(Pageable.class));
    }

    @DisplayName("Deve buscar todos os usuários paginados com sort desc")
    @Test
    void shouldBeGetAllUsersPaginatedWithDescSort () {
        var userResponseDtoPage = createUserResponseDtoPage();
        when(userGateway.getAllUsers(any(Pageable.class))).thenReturn(userResponseDtoPage);

        assertEquals(userResponseDtoPage, readAllUserCase.run(0, 10, "name,desc"));

        verify(userGateway, times(1)).getAllUsers(any(Pageable.class));
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

    private Page<UserResponseDto> createUserResponseDtoPage() {
        var userResponseDtoList = new ArrayList<UserResponseDto>();

        userResponseDtoList.add(createUserResponseDto());
        userResponseDtoList.add(createUserResponseDto());
        userResponseDtoList.add(createUserResponseDto());
        userResponseDtoList.add(createUserResponseDto());

        return new PageImpl<>(userResponseDtoList);
    }

}
