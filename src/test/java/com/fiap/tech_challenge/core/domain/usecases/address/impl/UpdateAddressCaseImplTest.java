package com.fiap.tech_challenge.core.domain.usecases.address.impl;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.address.UpdateAddressCase;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.AddressMapper;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UpdateAddressCaseImplTest {
//
//    @Mock
//    private AddressGateway addressGateway;
//
//    private UpdateAddressCase updateAddressCase;
//    private AddressMapper addressMapper;
//    private UserMapper userMapper;
//
//    private AutoCloseable mock;
//
//    private String addressIdTest;
//    private String streetTest;
//    private String numberTest;
//    private String complementTest;
//    private String neighborhoodTest;
//    private String cityTest;
//    private String stateTest;
//    private String countryTest;
//    private String postalCodeTest;
//    private LocalDateTime createdAtTest;
//    private LocalDateTime updatedAtTest;
//    private UserDomain userTest;
//
//    @BeforeEach
//    void setup () {
//        mock = MockitoAnnotations.openMocks(this);
//        updateAddressCase = new UpdateAddressCaseImpl(addressGateway);
//
//        addressIdTest = UUID.randomUUID().toString();
//        streetTest = "Rua Beija-flor";
//        numberTest = "1000";
//        complementTest = "";
//        neighborhoodTest = "São Cristóvão";
//        cityTest = "Itacoatiara";
//        stateTest = "AM";
//        countryTest = "Brasil";
//        postalCodeTest = "69103-312";
//        createdAtTest = LocalDateTime.now();
//        updatedAtTest = LocalDateTime.now();
//        userTest = new UserDomain(
//                UUID.randomUUID().toString(),
//                "John",
//                "john@email.com",
//                "12345678900",
//                "99999999999",
//                "password",
//                LocalDateTime.now(),
//                LocalDateTime.now(),
//                UserType.CLIENT,
//                new ArrayList<>()
//        );;
//    }
//
//    @DisplayName("Deve atualizar o endereço")
//    @Test
//    void shouldBeUpdateAddress () {
//        var addressDomain = createAddressDomain();
//        var addressResponseDto = createAddressResponseDto();
//        when(addressGateway.searchAddressById(anyString())).thenReturn(addressDomain);
//        when(addressGateway.createAddress(any(AddressDomain.class))).thenReturn(addressResponseDto);
//
//        var updatedAddressDomain = updateAddressCase.run(addressIdTest, addressDomain);
//
//        assertEquals(addressResponseDto, updatedAddressDomain);
//        verify(addressGateway, times(1)).searchAddressById(anyString());
//        verify(addressGateway, times(1)).createAddress(addressDomain);
//    }
//
//    private AddressDomain createAddressDomain () {
//        return new AddressDomain(
//                addressIdTest,
//                streetTest,
//                numberTest,
//                complementTest,
//                neighborhoodTest,
//                cityTest,
//                stateTest,
//                countryTest,
//                postalCodeTest,
//                createdAtTest,
//                updatedAtTest,
//                userTest
//        );
//    }
//
//    private AddressResponseDto createAddressResponseDto () {
//        return new AddressResponseDto(
//                addressIdTest,
//                streetTest,
//                numberTest,
//                complementTest,
//                neighborhoodTest,
//                cityTest,
//                stateTest,
//                countryTest,
//                postalCodeTest
//        );
//    }

}
