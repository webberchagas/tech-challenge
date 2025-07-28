package com.fiap.tech_challenge.core.domain.usecases.address.impl;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.address.CreateAddressCase;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class CreateAddressCaseImplTest {

    @Mock
    private AddressGateway addressGateway;
    @Mock
    private UserGateway userGateway;

    private AutoCloseable mock;

    private CreateAddressCase createAddressCase;

    private String addressIdTest;
    private String streetTest;
    private String numberTest;
    private String complementTest;
    private String neighborhoodTest;
    private String cityTest;
    private String stateTest;
    private String countryTest;
    private String postalCodeTest;
    private LocalDateTime createdAtTest;
    private LocalDateTime updatedAtTest;
    private UserDomain userTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        createAddressCase = new CreateAddressCaseImpl(addressGateway, userGateway);

        addressIdTest = "0ea0d8bb-bc69-4977-b2f4-536c335fdae4";
        streetTest = "Rua Beija-flor";
        numberTest = "1000";
        complementTest = "";
        neighborhoodTest = "São Cristóvão";
        cityTest = "Itacoatiara";
        stateTest = "AM";
        countryTest = "Brasil";
        postalCodeTest = "69103-312";
        userTest = new UserDomain(
                "0ea0d8bb-bc69-4977-b2f4-536c335fdae4",
                "John",
                "john@email.com",
                "12345678900",
                "99999999999",
                "password",
                LocalDateTime.now(),
                LocalDateTime.now(),
                UserType.CLIENT,
                new ArrayList<>()
        );
    }

    @DisplayName("Deve criar um novo endereço para um usuário")
    @Test
    void shouldBeCreateNewAddressForUser() {
        var addressDomain = createAddressDomain();
        when(userGateway.searchUserById(any())).thenReturn(userTest);
        when(addressGateway.createAddress(any()))
                .thenReturn(createAddressResponseDto());

        var createdAddressDomain = createAddressCase.run(userTest.getUserId(), addressDomain);

        assertNotNull(addressDomain.getCreatedAt());
        assertNotNull(addressDomain.getUpdatedAt());
        assertNotNull(createdAddressDomain.getAddressId());
        verify(userGateway, times(1)).searchUserById(any());
        verify(addressGateway, times(1)).createAddress(any());
    }

    private AddressDomain createAddressDomain() {
        return new AddressDomain(
                null,
                streetTest,
                numberTest,
                complementTest,
                neighborhoodTest,
                cityTest,
                stateTest,
                countryTest,
                postalCodeTest,
                null,
                null,
                null
        );
    }

    private AddressResponseDto createAddressResponseDto() {
        return new AddressResponseDto(
                UUID.randomUUID().toString(),
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
