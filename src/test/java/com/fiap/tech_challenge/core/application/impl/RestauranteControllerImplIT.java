package com.fiap.tech_challenge.core.application.impl;

import com.fiap.tech_challenge.core.dto.address.AddressRequestDto;
import com.fiap.tech_challenge.core.dto.restaurant.RestaurantRequestDto;
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/db_clean.sql", "/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RestauranteControllerImplIT {
    @LocalServerPort
    private int port;

    private String restaurantIdTest;
    private String restaurantNameTest;
    private String cnpjTest;
    private String cuisineTypeTest;
    private String openingHoursTest;
    private AddressRequestDto addressTest;
    private String userIdTest;

    private Integer pageTest;
    private Integer sizeTest;
    private String sortTest;

    @BeforeEach
    void setup () {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        restaurantIdTest = "aaaa1111-aaaa-bbbb-cccc-aaaa11111111";
        restaurantNameTest = "Bella Italia";
        cnpjTest = "12345678000190";
        cuisineTypeTest = "Italian";
        openingHoursTest = "Seg a Sex das 18h às 22h, Sáb e Dom 17h às 23h";
        addressTest = new AddressRequestDto(
                "Avenida Paulista",
                "2030",
                "Apt 101",
                "Jardins",
                "São Paulo",
                "SP",
                "Brasil",
                "01311000"
        );
        userIdTest = "9116788f-cf9d-40e2-a06d-6f2a831ec362";
        pageTest = 0;
        sizeTest = 10;
        sortTest = "restaurantName,asc";
    }

    @DisplayName("Deve permitir criar um novo restaurante")
    @Test
    void shouldBeCreateNewRestaurant () {
        var restaurantRequestDto = createRestaurantRequestDto();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restaurantRequestDto)
        .when()
                .post("/api/v1/restaurants")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("$", hasKey("restaurantId"))
                .body("$", hasKey("restaurantName"))
                .body("$", hasKey("cnpj"))
                .body("$", hasKey("cuisineType"))
                .body("$", hasKey("openingHours"))
                .body("$", hasKey("address"))
                .body("$", hasKey("user"));
    }

    @DisplayName("Deve lançar exceção quando o CNPJ já existe")
    @Test
    void shouldBeThrowExceptionWhenCNPJAlreadyExists () {
        cnpjTest = "12345678000199";
        var restaurantRequestDto = createRestaurantRequestDto();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(restaurantRequestDto)
        .when()
                .post("/api/v1/restaurants")
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @DisplayName("Deve permitir buscar restaurante por ID")
    @Test
    void shouldBeGetRestaurantById () {
        when()
                .get("/api/v1/restaurants/" + restaurantIdTest)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasKey("restaurantId"))
                .body("$", hasKey("restaurantName"))
                .body("$", hasKey("cnpj"))
                .body("$", hasKey("cuisineType"))
                .body("$", hasKey("openingHours"))
                .body("$", hasKey("address"))
                .body("$", hasKey("user"));
    }

    @DisplayName("Deve lançar exceção quando buscar um restaurante com ID inexistente")
    @Test
    void shouldBeThrowNotFoundExceptionWhenGetResturantWithInexistentId () {
        when()
                .get("/api/v1/restaurants/" + UUID.randomUUID().toString())
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @DisplayName("Deve permitir remover restaurante por ID")
    @Test
    void shouldBeDeleteRestaurantById () {
        when()
                .delete("/api/v1/restaurants/" + restaurantIdTest)
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @DisplayName("Deve lançar exceção quando remover restaurante por ID inexistente")
    @Test
    void shouldBeThrowExceptionWhenDeleteRestaurantWithInexistentId () {
        when()
                .delete("/api/v1/restaurants/" + UUID.randomUUID().toString())
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @DisplayName("Deve permitir buscar restaurantes paginado")
    @Test
    void shouldBeGetPaginatedRestaurants () {
        when()
                .get("/api/v1/restaurants?page=" + pageTest + "&size=" + sizeTest + "&sort=" + sortTest)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasKey("totalElements"))
                .body("totalElements", equalTo(3));
    }

    private RestaurantRequestDto createRestaurantRequestDto () {
        return new RestaurantRequestDto(
                restaurantNameTest,
                cnpjTest,
                cuisineTypeTest,
                openingHoursTest,
                addressTest,
                userIdTest
        );
    }

}
