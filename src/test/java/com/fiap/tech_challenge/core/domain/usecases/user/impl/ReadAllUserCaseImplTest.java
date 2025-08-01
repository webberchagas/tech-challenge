package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.user.ReadAllUserCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReadAllUserCaseImplTest {

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
    private final Integer page = 0;
    private final Integer size = 2;
    private final String sort = "name,asc";


    @BeforeEach
    void setup() {
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
    void tearDown() throws Exception {
        mock.close();
    }

    @DisplayName("Deve buscar todos os usuários paginados")
    @Test
    void shouldBeGetAllUsersPaginated () {
        var userPage = createPage();
        when(userGateway.getAllUsers(page, size, sort)).thenReturn(userPage);

        assertEquals(userPage, readAllUserCase.run(0, 10, "name,asc"));

        verify(userGateway, times(1)).getAllUsers(page, size, sort);
    }

    @DisplayName("Deve buscar todos os usuários paginados sem sort")
    @Test
    void shouldBeGetAllUsersPaginatedWithoutSort() {
        var userResponseDtoPage = createPage();
        when(userGateway.getAllUsers(page, size, null)).thenReturn(userResponseDtoPage);

        assertEquals(userResponseDtoPage, readAllUserCase.run(0, 10, null));

        verify(userGateway, times(1)).getAllUsers(page, size, null);
    }

    @DisplayName("Deve buscar todos os usuários paginados com sort vazio")
    @Test
    void shouldBeGetAllUsersPaginatedWithEmptySort() {
        var userPage = createPage();
        when(userGateway.getAllUsers(page, size, "")).thenReturn(userPage);

        assertEquals(userPage, readAllUserCase.run(0, 10, ""));

        verify(userGateway, times(1)).getAllUsers(page, size, null);
    }

    @DisplayName("Deve buscar todos os usuários paginados com sort sem direction")
    @Test
    void shouldBeGetAllUsersPaginatedWithSortWithoutDirection() {
        var userPage = createPage();
        when(userGateway.getAllUsers(page, size, sort)).thenReturn(userPage);

        assertEquals(userPage, readAllUserCase.run(0, 10, "name"));

        verify(userGateway, times(1)).getAllUsers(page,  size, sort);
    }

    @DisplayName("Deve buscar todos os usuários paginados com sort desc")
    @Test
    void shouldBeGetAllUsersPaginatedWithDescSort() {
        var userPage = createPage();
        when(userGateway.getAllUsers(page, size, sort)).thenReturn(userPage);

        assertEquals(userPage, readAllUserCase.run(0, 10, "name,desc"));

        verify(userGateway, times(1)).getAllUsers(page, size, sort);
    }

    private UserDomain createUserDomain() {
        return new UserDomain(
                userIdTest,
                nameTest,
                emailTest,
                documentNumberTest,
                phoneTest,
                null,
                null,
                null,
                userTypeTest,
                null
        );
    }

    private PageResultDomain<UserDomain> createPage() {
        var userList = new ArrayList<UserDomain>();

        userList.add(createUserDomain());
        userList.add(createUserDomain());
        userList.add(createUserDomain());
        userList.add(createUserDomain());

        return new PageResultDomain<>(userList, page, size, 4);
    }

}
