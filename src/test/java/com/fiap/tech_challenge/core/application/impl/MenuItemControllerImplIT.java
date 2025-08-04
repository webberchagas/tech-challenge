package com.fiap.tech_challenge.core.application.impl;

import com.fiap.tech_challenge.core.dto.menu.MenuItemRequestDto;
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

import java.math.BigDecimal;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasKey;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/db_clean.sql", "/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MenuItemControllerImplIT {

    @LocalServerPort
    private int port;

    private String nameTest;
    private String descriptionTest;
    private BigDecimal priceTest;
    private boolean availableInStoreOnlyTest;
    private String photoPathTest;
    private String restaurantIdTest;
    private Integer pageTest;
    private Integer sizeTest;
    private String sortTest;

    @BeforeEach
    void setup () {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        nameTest = "404 Cat";
        descriptionTest = "It's only a cat";
        priceTest = BigDecimal.valueOf(404.00);
        availableInStoreOnlyTest = true;
        photoPathTest = "https://http.cat/images/404.jpg";
        restaurantIdTest = "aaaa1111-aaaa-bbbb-cccc-aaaa11111111";
        pageTest = 0;
        sizeTest = 10;
        sortTest = "menuItemId,asc";
    }

    @DisplayName("Deve permitir criar um item no menu")
    @Test
    void shouldBeCreateNewMenuItem () {
        var menuItemRequestDto = createMenuItemRequestDto();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(menuItemRequestDto)
        .when()
                .post("/api/v1/menu")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("$", hasKey("menuItemId"))
                .body("$", hasKey("name"))
                .body("$", hasKey("description"))
                .body("$", hasKey("price"))
                .body("$", hasKey("availableInStoreOnly"))
                .body("$", hasKey("photoPath"))
                .body("$", hasKey("restaurant"));
    }

    @DisplayName("Deve lançar exceção ao criar um item no menu com restaurante que não existe")
    @Test
    void shouldBeThrowExceptionWhenCreateNewMenuItemWithRestaurantIdInexistent () {
        restaurantIdTest = UUID.randomUUID().toString();
        var menuItemRequestDto = createMenuItemRequestDto();

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(menuItemRequestDto)
        .when()
                .post("/api/v1/menu")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @DisplayName("Deve permitir buscar todos os itens de menu de um restaurante paginado")
    @Test
    void shouldBeGetAllPaginatedRestaurantMenuItems () {
        when()
                .get("/api/v1/menu?restaurantId=" + restaurantIdTest + "&page=" + pageTest + "&size=" + sizeTest + "&sort" + sortTest)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasKey("totalElements"));
    }

    @DisplayName("Deve lançar exceção ao buscar todos os itens de menu de um restaurante paginado com um restaurante que não existe")
    @Test
    void shouldBeThrowExceptionWhenGetAllPaginatedRestaurantMenuItemsWithInexistentRestaurant () {
        restaurantIdTest = UUID.randomUUID().toString();
        when()
                .get("/api/v1/menu?restaurantId=" + restaurantIdTest + "&page=" + pageTest + "&size=" + sizeTest + "&sort" + sortTest)
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private MenuItemRequestDto createMenuItemRequestDto() {
        return new MenuItemRequestDto(
                nameTest,
                descriptionTest,
                priceTest,
                availableInStoreOnlyTest,
                photoPathTest,
                restaurantIdTest
        );
    }

}
