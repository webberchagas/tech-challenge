package com.fiap.tech_challenge.core.application.impl;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.model.RestaurantDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.*;
import com.fiap.tech_challenge.core.dto.address.AddressRequestDto;
import com.fiap.tech_challenge.core.dto.restaurant.RestaurantRequestDto;
import com.fiap.tech_challenge.core.dto.restaurant.RestaurantResponseDto;
import com.fiap.tech_challenge.core.exception.ControllerExceptionHandler;
import com.fiap.tech_challenge.helper.TestHelper;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.RestaurantMapper;
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
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestauranteControllerImplTest {

    @Mock
    private CreateRestaurantCase createRestaurantCase;
    @Mock
    private ReadRestaurantByIdCase readRestaurantByIdCase;
    @Mock
    private UpdateRestaurantCase updateRestaurantCase;
    @Mock
    private DeleteRestaurantCase deleteRestaurantCase;
    @Mock
    private ReadAllRestaurantCase readAllRestaurantCase;
    @Mock
    private RestaurantMapper restaurantMapper;

    private AutoCloseable mock;

    private MockMvc mockMvc;

    private String restaurantIdTest;
    private String restaurantNameTest;
    private String cnpjTest;
    private String cuisineTypeTest;
    private String openingHoursTest;
    private AddressRequestDto addressTest;
    private String userIdTest;
    private AddressDomain addressDomainTest;
    private UserDomain userDomainTest;
    private LocalDateTime createdAtTest;
    private LocalDateTime updatedAtTest;

    private Integer pageTest;
    private Integer sizeTest;
    private String sortTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);

        var restauranteController = new RestaurantControllerImpl(
                createRestaurantCase,
                readRestaurantByIdCase,
                updateRestaurantCase,
                deleteRestaurantCase,
                readAllRestaurantCase,
                restaurantMapper
        );

        mockMvc = MockMvcBuilders
                .standaloneSetup(restauranteController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();

        restaurantIdTest = UUID.randomUUID().toString();
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
        userIdTest = UUID.randomUUID().toString();
        userDomainTest = new UserDomain(
                UUID.randomUUID().toString(),
                "John",
                "john@email.com",
                "12345678900",
                "88999999999",
                "password",
                LocalDateTime.now(),
                LocalDateTime.now(),
                UserType.CLIENT,
                null
        );
        addressDomainTest = new AddressDomain(
                UUID.randomUUID().toString(),
                "Avenida Paulista",
                "2030",
                "Apt 101",
                "Jardins",
                "São Paulo",
                "SP",
                "Brasil",
                "01311000",
                LocalDateTime.now(),
                LocalDateTime.now(),
                userDomainTest
        );
        createdAtTest = LocalDateTime.now();
        updatedAtTest = LocalDateTime.now();
        pageTest = 0;
        sizeTest = 10;
        sortTest = "restauranteName,asc";
    }

    @AfterEach
    void tearDown () throws Exception {
        mock.close();
    }

    @DisplayName("Deve permitir criar um novo restaurante")
    @Test
    void shouldBeCreateNewRestaurant () throws Exception {
        var restaurantRequestDto = createRestaurantRequestDto();
        var restaurantDomain = createRestaurantDomain();
        var restaurantResponseDto = createRestaurantResponseDto();
        when(restaurantMapper.toDomain(any(RestaurantRequestDto.class))).thenReturn(restaurantDomain);
        when(createRestaurantCase.run(any(RestaurantDomain.class))).thenReturn(restaurantDomain);
        when(restaurantMapper.toResponseDto(any(RestaurantDomain.class))).thenReturn(restaurantResponseDto);

        mockMvc.perform(
                post("/api/v1/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.asJsonString(restaurantRequestDto))
        ).andExpect(status().isCreated())
                .andExpect(content().json(TestHelper.asJsonString(restaurantResponseDto)));

        verify(restaurantMapper, times(1)).toDomain(any(RestaurantRequestDto.class));
        verify(createRestaurantCase, times(1)).run(any(RestaurantDomain.class));
        verify(restaurantMapper, times(1)).toResponseDto(any(RestaurantDomain.class));
    }

    @DisplayName("Deve permitir buscar restaurante por ID")
    @Test
    void shouldBeGetRestaurantById () throws Exception {
        var restaurantDomain = createRestaurantDomain();
        var restaurantResponseDto = createRestaurantResponseDto();
        when(readRestaurantByIdCase.run(anyString())).thenReturn(restaurantDomain);
        when(restaurantMapper.toResponseDto(any(RestaurantDomain.class)))
                .thenReturn(restaurantResponseDto);

        mockMvc.perform(
                get("/api/v1/restaurants/{}", UUID.randomUUID().toString())
        ).andExpect(status().isOk())
                .andExpect(content().json(TestHelper.asJsonString(restaurantResponseDto)));
    }

    @DisplayName("Deve permitir remover restaurante por ID")
    @Test
    void shouldBeDeleteRestaurantById () throws Exception {
        doNothing().when(deleteRestaurantCase).run(anyString());

        mockMvc.perform(
                        delete("/api/v1/restaurants/{}", UUID.randomUUID().toString())
                ).andExpect(status().isNoContent());
    }

    @DisplayName("Deve permitir buscar restaurantes paginado")
    @Test
    void shouldBeGetPaginatedRestaurants () throws Exception {
        var restaurantDomainPaginated = createRestaurantDomainPage();
        var restaurantResponseDtoPage = createRestaurantResponseDtoPage();

        when(readAllRestaurantCase.run(pageTest, sizeTest, sortTest))
                .thenReturn(restaurantDomainPaginated);
        when(restaurantMapper.toResponseDto(any(RestaurantDomain.class)))
                .thenReturn(createRestaurantResponseDto());

        mockMvc.perform(
                get("/api/v1/restaurants?page=" + pageTest + "&size=" + sizeTest + "&sort=" + sortTest)
        ).andExpect(status().isOk())
                .andExpect(content().json(TestHelper.asJsonString(restaurantResponseDtoPage)));

        verify(readAllRestaurantCase, times(1)).run(pageTest, sizeTest, sortTest);
        verify(restaurantMapper, times(3)).toResponseDto(any(RestaurantDomain.class));
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

    private RestaurantDomain createRestaurantDomain () {
        return new RestaurantDomain(
                restaurantIdTest,
                restaurantNameTest,
                cnpjTest,
                cuisineTypeTest,
                openingHoursTest,
                addressDomainTest,
                userDomainTest,
                userIdTest,
                createdAtTest,
                updatedAtTest
        );
    }

    private RestaurantResponseDto createRestaurantResponseDto () {
        return new RestaurantResponseDto(
                restaurantIdTest,
                restaurantNameTest,
                cnpjTest,
                cuisineTypeTest,
                openingHoursTest,
                null,
                null
        );
    }

    private PageResultDomain<RestaurantDomain> createRestaurantDomainPage () {
        var restaurantResponseDtoList = new ArrayList<RestaurantDomain>();

        restaurantResponseDtoList.add(createRestaurantDomain());
        restaurantResponseDtoList.add(createRestaurantDomain());
        restaurantResponseDtoList.add(createRestaurantDomain());

        return new PageResultDomain<>(
                restaurantResponseDtoList,
                1,
                restaurantResponseDtoList.size(),
                restaurantResponseDtoList.size()
        );
    }

    private PageResultDomain<RestaurantResponseDto> createRestaurantResponseDtoPage () {
        var restaurantResponseDtoList = new ArrayList<RestaurantResponseDto>();

        restaurantResponseDtoList.add(createRestaurantResponseDto());
        restaurantResponseDtoList.add(createRestaurantResponseDto());
        restaurantResponseDtoList.add(createRestaurantResponseDto());

        return new PageResultDomain<>(
                restaurantResponseDtoList,
                1,
                restaurantResponseDtoList.size(),
                restaurantResponseDtoList.size()
        );
    }

}
