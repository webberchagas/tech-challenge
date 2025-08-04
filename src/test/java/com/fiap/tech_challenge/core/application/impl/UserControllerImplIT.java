package com.fiap.tech_challenge.core.application.impl;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.dto.address.AddressRequestDto;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserCreationRequestDto;
import com.fiap.tech_challenge.core.dto.user.UserUpdateRequestDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/db_load.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/db_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserControllerImplIT {
    @LocalServerPort
    private int port;

    private String userIdTest;
    private String nameTest;
    private String documentNumberTest;
    private String emailTest;
    private String phoneTest;
    private String passwordTest;
    private UserType userTypeTest;
    private List<AddressRequestDto> addressRequestDtoTest;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        userIdTest = "05e71b88-8d37-4e7f-a055-f3af8d249939";
        nameTest = "John";
        documentNumberTest = "12345678900";
        emailTest = "john@email.com";
        phoneTest = "88999999999";
        passwordTest = "password";
        userTypeTest = UserType.CLIENT;
        addressRequestDtoTest = new ArrayList<>();
    }

    @DisplayName("Deve criar um usuário")
    @Test
    void shouldBeCreateUser() {
        var userCreationRequestDto = createUserCreationRequestDto();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userCreationRequestDto)
        .when()
                .post("/api/v1/users")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("$", hasKey("userId"))
                .body("$", hasKey("name"))
                .body("$", hasKey("documentNumber"))
                .body("$", hasKey("email"))
                .body("$", hasKey("phone"))
                .body("$", hasKey("userType"))
                .body("$", hasKey("address"));
    }

    @DisplayName("Deve atualizar um usuário")
    @Test
    void shouldBeUpdateUser() {
        var userUpdateRequestDto = createUserUpdateRequestDto();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userUpdateRequestDto)
        .when()
                .put("/api/v1/users/" + userIdTest)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasKey("userId"))
                .body("$", hasKey("name"))
                .body("$", hasKey("documentNumber"))
                .body("$", hasKey("email"))
                .body("$", hasKey("phone"))
                .body("$", hasKey("userType"));
    }

    @DisplayName("Deve lançar exceção quando tentar atualizar o tipo de usuário de um dono de restaurante com restaurantes cadastrado")
    @Test
    void shouldBeThrowExceptionWhenTryUpdateUserTypeWithRestaurantOwnerWithRegisteredRestaurants() {
        userIdTest = "9116788f-cf9d-40e2-a06d-6f2a831ec362";
        var userUpdateRequestDto = createUserUpdateRequestDto();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userUpdateRequestDto)
        .when()
                .put("/api/v1/users/" + userIdTest)
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    private UserCreationRequestDto createUserCreationRequestDto() {
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

    private UserUpdateRequestDto createUserUpdateRequestDto() {
        return new UserUpdateRequestDto(
                nameTest,
                documentNumberTest,
                emailTest,
                phoneTest,
                userTypeTest
        );
    }

}
