package com.fiap.tech_challenge.infrastructure.application.impl;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.user.*;
import com.fiap.tech_challenge.core.dto.address.AddressRequestDto;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserCreationRequestDto;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserUpdateRequestDto;
import com.fiap.tech_challenge.core.exception.ControllerExceptionHandler;
import com.fiap.tech_challenge.helper.TestHelper;
import com.fiap.tech_challenge.infrastructure.application.UserController;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.UserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerImplTest {

    @Mock
    private CreateUserCase createUserCase;
    @Mock
    private ReadAllUserCase readAllUserCase;
    @Mock
    private ReadUserByIdCase readUserByIdCase;
    @Mock
    private UpdateUserCase updateUserCase;
    @Mock
    private DeleteUserCase deleteUserCase;
    @Mock
    private UserMapper userMapper;

    private MockMvc mockMvc;

    private AutoCloseable mock;

    private String userIdTest;
    private String nameTest;
    private String documentNumberTest;
    private String emailTest;
    private String phoneTest;
    private String passwordTest;
    private UserType userTypeTest;
    private LocalDateTime createdAtTest;
    private LocalDateTime updatedAtTest;
    private List<AddressRequestDto> addressRequestDtoTest;
    private List<AddressResponseDto> addressResponseDtoTest;
    private List<AddressDomain> addressDomainTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);

        UserController userController = new UserControllerImpl(
                createUserCase,
                readAllUserCase,
                readUserByIdCase,
                updateUserCase,
                deleteUserCase,
                userMapper
        );

        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();

        userIdTest = UUID.randomUUID().toString();
        nameTest = "John";
        documentNumberTest = "12345678900";
        emailTest = "john@email.com";
        phoneTest = "88999999999";
        passwordTest = "password";
        createdAtTest = LocalDateTime.now();
        updatedAtTest = LocalDateTime.now();
        userTypeTest = UserType.CLIENT;
        addressRequestDtoTest = new ArrayList<>();
        addressResponseDtoTest = new ArrayList<>();
        addressDomainTest = new ArrayList<>();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @DisplayName("Deve criar um usuário")
    @Test
    void shouldBeCreateUser () throws Exception {
        var userCreationRequestDto = createUserCreationRequestDto();
        var userDomain = createUserDomain();
        var userResponseDto = createUserResponseDto();
        when(userMapper.toDomain(any(UserCreationRequestDto.class))).thenReturn(userDomain);
        when(userMapper.toResponseDto(any(UserDomain.class))).thenReturn(userResponseDto);
        when(createUserCase.run(any())).thenReturn(userDomain);

        mockMvc.perform(
                post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.asJsonString(userCreationRequestDto))
        ).andExpect(status().isCreated())
                .andExpect(content().json(TestHelper.asJsonString(userResponseDto)));

        verify(createUserCase, times(1)).run(any());
    }

    @DisplayName("Deve buscar um usuário pelo ID")
    @Test
    void shouldBeGetUserById () throws Exception {
        var userResponseDto = createUserResponseDto();
        var userDomain = createUserDomain();
        when(readUserByIdCase.run(anyString())).thenReturn(userDomain);
        when(userMapper.toResponseDto(any(UserDomain.class))).thenReturn(userResponseDto);

        mockMvc.perform(
                        get("/api/v1/users/" + userIdTest)
                ).andExpect(status().isOk())
                .andExpect(content().json(TestHelper.asJsonString(userResponseDto)));

        verify(readUserByIdCase, times(1)).run(anyString());
    }

    @DisplayName("Deve remover um usuário")
    @Test
    void shouldBeDeleteUser () throws Exception {
        doNothing().when(deleteUserCase).run(anyString());

        mockMvc.perform(
                        delete("/api/v1/users/" + userIdTest)
                ).andExpect(status().isNoContent());

        verify(deleteUserCase, times(1)).run(anyString());
    }

    @DisplayName("Deve atualizar um usuário")
    @Test
    void shouldBeUpdateUser () throws Exception {
        var userResponseDto = createUserResponseDto();
        var userUpdateRequestDto = createUserUpdateRequestDto();
        var userDomain = createUserDomain();
        addressResponseDtoTest = null;
        when(updateUserCase.run(anyString(), any())).thenReturn(userDomain);
        when(userMapper.toDomainUpdate(any(UserUpdateRequestDto.class))).thenReturn(userDomain);
        when(userMapper.toResponseDtoWithoutAddress(any(UserDomain.class))).thenReturn(userResponseDto);

        mockMvc.perform(
                put("/api/v1/users/" + userIdTest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.asJsonString(userUpdateRequestDto))
        ).andExpect(status().isOk())
                .andExpect(content().json(TestHelper.asJsonString(userResponseDto)));

        verify(updateUserCase, times(1)).run(anyString(), any());
        verify(userMapper, times(1)).toDomainUpdate(any());
        verify(userMapper, times(1)).toResponseDtoWithoutAddress(any());
    }

    private UserCreationRequestDto createUserCreationRequestDto () {
        return new UserCreationRequestDto(
                nameTest,
                documentNumberTest,
                emailTest,
                phoneTest,
                passwordTest,
                userTypeTest,
                addressRequestDtoTest
        );
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
                addressDomainTest
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
            addressResponseDtoTest
        );
    }

    private UserUpdateRequestDto createUserUpdateRequestDto () {
        return new UserUpdateRequestDto(
                nameTest,
                documentNumberTest,
                emailTest,
                phoneTest,
                userTypeTest
        );
    }

}
