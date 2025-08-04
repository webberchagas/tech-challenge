package com.fiap.tech_challenge.core.application.impl;

import com.fiap.tech_challenge.core.dto.address.AddressRequestDto;
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

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasKey;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/db_clean.sql", "/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AddressControllerImplIT {

    @LocalServerPort
    private int port;

    private String userIdTest;
    private String addressIdTest;
    private String streetTest;
    private String numberTest;
    private String complementTest;
    private String neighborhoodTest;
    private String cityTest;
    private String stateTest;
    private String countryTest;
    private String postalCodeTest;

    @BeforeEach
    void setup () {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        userIdTest = "05e71b88-8d37-4e7f-a055-f3af8d249939";
        addressIdTest = "4a25c2fd-a05a-4b05-9e89-ea75a32adc29";
        streetTest = "Av. Teste";
        numberTest = "1000";
        complementTest = "Complemento";
        neighborhoodTest = "Um bairro bacana";
        cityTest = "Uma cidade linda";
        stateTest = "TE";
        countryTest = "Um belo país";
        postalCodeTest = "99999999";
    }

    @DisplayName("Deve permitir buscar os endereços de um usuário")
    @Test
    void shouldBeGetAddressByUser () {
        when()
                .get("/api/v1/addresses/by-user/" + userIdTest)
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("Deve lançar exceção buscar os endereços de um usuário que não existe")
    @Test
    void shouldBeThrowExceptionWhenGetAddressByInexistentUser () {
        userIdTest = UUID.randomUUID().toString();

        when()
                .get("/api/v1/addresses/by-user/" + userIdTest)
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @DisplayName("Deve permitir criar um endereço")
    @Test
    void shouldBeCreateAnAddress () {
        var addressRequestDto = createAddressRequestDto();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(addressRequestDto)
        .when()
                .post("/api/v1/addresses/" + userIdTest)
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("$", hasKey("addressId"))
                .body("$", hasKey("street"))
                .body("$", hasKey("number"))
                .body("$", hasKey("complement"))
                .body("$", hasKey("neighborhood"))
                .body("$", hasKey("city"))
                .body("$", hasKey("state"))
                .body("$", hasKey("country"))
                .body("$", hasKey("postalCode"));
    }

    @DisplayName("Deve permitir atualizar um endereço")
    @Test
    void shouldBeUpdateAnAddress () {
        var addressRequestDto = createAddressRequestDto();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(addressRequestDto)
        .when()
                .put("/api/v1/addresses/" + addressIdTest)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasKey("addressId"))
                .body("$", hasKey("street"))
                .body("$", hasKey("number"))
                .body("$", hasKey("complement"))
                .body("$", hasKey("neighborhood"))
                .body("$", hasKey("city"))
                .body("$", hasKey("state"))
                .body("$", hasKey("country"))
                .body("$", hasKey("postalCode"));
    }

    @DisplayName("Deve permitir remover um endereço")
    @Test
    void shouldBeDeleteAnAddress () {
        when()
                .delete("/api/v1/addresses/" + addressIdTest)
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private AddressRequestDto createAddressRequestDto () {
        return new AddressRequestDto(
                streetTest,
                numberTest,
                complementTest,
                neighborhoodTest,
                cityTest,
                stateTest,
                countryTest,
                postalCodeTest
        );
    }

}
