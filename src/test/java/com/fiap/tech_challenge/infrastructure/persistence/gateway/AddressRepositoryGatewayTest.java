package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserAddressEntity;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.AddressMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.UserAddressRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AddressRepositoryGatewayTest {

    private AutoCloseable mock;

    private AddressRepositoryGateway addressRepositoryGateway;

    @Mock
    private UserAddressRepository addressRepository;
    @Mock
    private AddressMapper addressMapper;

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
        addressRepositoryGateway = new AddressRepositoryGateway(addressRepository, addressMapper);
        addressIdTest = UUID.randomUUID().toString();
        streetTest = "Rua Beija-flor";
        numberTest = "1000";
        complementTest = "";
        neighborhoodTest = "São Cristóvão";
        cityTest = "Itacoatiara";
        stateTest = "AM";
        countryTest = "Brasil";
        postalCodeTest = "69103-312";
        createdAtTest = LocalDateTime.now();
        updatedAtTest = LocalDateTime.now();
        userTest = new UserDomain(
                UUID.randomUUID().toString(),
                "John",
                "john@email.com",
                "12345678988",
                "88999999999",
                "password",
                LocalDateTime.now(),
                LocalDateTime.now(),
                UserType.CLIENT,
                null
        );
    }

    @AfterEach
    void tearDown () throws Exception {
        mock.close();
    }

    @DisplayName("Deve criar um endereço")
    @Test
    void shouldBeCreateAnAddress () {
        var addressEntity = createAddressEntity();
        var addressResponseDto = createAddressResponseDto();
        var addressDomain = createAddressDomain();
        when(addressMapper.toAddressEntity(any())).thenReturn(addressEntity);
        when(addressRepository.save(any())).thenReturn(addressEntity);
        when(addressMapper.toAddressResponse(any())).thenReturn(addressResponseDto);

        var createdAddress = addressRepositoryGateway.createAddress(addressDomain);

        assertEquals(addressResponseDto, createdAddress);
        verify(addressMapper, times(1)).toAddressEntity(any());
        verify(addressMapper, times(1)).toAddressResponse(any());
        verify(addressRepository, times(1)).save(any());
    }

    @DisplayName("Deve buscar um endereço por ID")
    @Test
    void shouldBeFindAndAddressWithID () {
        var addressEntity = createAddressEntity();
        when(addressRepository.findById(anyString())).thenReturn(Optional.of(addressEntity));

        var foundAddress = addressRepositoryGateway.searchAddressById(UUID.randomUUID().toString());

        assertEquals(addressEntity, foundAddress);
        verify(addressRepository, times(1)).findById(any());
    }

    @DisplayName("Deve lançar exceção quando buscar um endereço com ID inexistente")
    @Test
    void shouldBeThrowNotFoundExceptionWhenFindAddressWithInexistentID () {
        when(addressRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> addressRepositoryGateway.searchAddressById(UUID.randomUUID().toString())
        );

        verify(addressRepository, times(1)).findById(any());
    }

    @DisplayName("Deve remover um endereço")
    @Test
    void shouldBeRemoveAnAddress () {
        when(addressRepository.findById(anyString())).thenReturn(Optional.of(createAddressEntity()));
        doNothing().when(addressRepository).delete(any());

        addressRepositoryGateway.deleteAddressById(addressIdTest);

        verify(addressRepository, times(1)).delete(any());
        verify(addressRepository, times(1)).findById(addressIdTest);
    }

    @DisplayName("Deve lançar NotFoundException quando tentar remover um endereço com ID inexistente")
    @Test
    void shouldBeThrowNotFoundExceptionWhenTryRemoveAnAddressWithInexistentId () {
        when(addressRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> addressRepositoryGateway.deleteAddressById(addressIdTest)
        );

        verify(addressRepository, times(1)).findById(addressIdTest);
    }

    private UserAddressEntity createAddressEntity () {
        return new UserAddressEntity(
                addressIdTest,
                streetTest,
                numberTest,
                complementTest,
                neighborhoodTest,
                cityTest,
                stateTest,
                countryTest,
                postalCodeTest,
                createdAtTest,
                updatedAtTest,
                null
        );
    }

    private AddressDomain createAddressDomain () {
        return new AddressDomain(
                addressIdTest,
                streetTest,
                numberTest,
                complementTest,
                neighborhoodTest,
                cityTest,
                stateTest,
                countryTest,
                postalCodeTest,
                createdAtTest,
                updatedAtTest,
                userTest
        );
    }

    private AddressResponseDto createAddressResponseDto () {
        return new AddressResponseDto(
                addressIdTest,
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
