package com.fiap.tech_challenge.core.application.impl;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.dto.address.AddressRequestDto;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserCreationRequestDto;
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
    private LocalDateTime createdAtTest;
    private LocalDateTime updatedAtTest;
    private List<AddressRequestDto> addressRequestDtoTest;
    private List<AddressResponseDto> addressResponseDtoTest;
    private List<AddressDomain> addressDomainTest;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

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

}
