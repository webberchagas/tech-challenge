package com.fiap.tech_challenge.core.domain.model;

import com.fiap.tech_challenge.core.domain.model.type.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddressDomainTest {

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
        addressIdTest = "0ea0d8bb-bc69-4977-b2f4-536c335fdae4";
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

    @DisplayName("Deve criar um objeto de AddressDomain")
    @Test
    void shouldBeCreateAddressDomainObject() {
        addressIdTest = null;

        var addressDomain = createAddressDomain();

        assertNull(addressDomain.getAddressId());
        assertEquals(streetTest, addressDomain.getStreet());
        assertEquals(numberTest, addressDomain.getNumber());
        assertEquals(complementTest, addressDomain.getComplement());
        assertEquals(neighborhoodTest, addressDomain.getNeighborhood());
        assertEquals(cityTest, addressDomain.getCity());
        assertEquals(stateTest, addressDomain.getState());
        assertEquals(countryTest, addressDomain.getCountry());
        assertEquals(postalCodeTest, addressDomain.getPostalCode());
        assertEquals(createdAtTest, addressDomain.getCreatedAt());
        assertEquals(updatedAtTest, addressDomain.getUpdatedAt());
        assertEquals(userTest, addressDomain.getUser());
    }

    @DisplayName("Deve criar um objeto de AddressDomain com ID")
    @Test
    void shouldBeCreateAddressDomainObjectWithId() {
        var addressDomain = createAddressDomain();

        assertEquals(addressIdTest, addressDomain.getAddressId());
        assertEquals(streetTest, addressDomain.getStreet());
        assertEquals(numberTest, addressDomain.getNumber());
        assertEquals(complementTest, addressDomain.getComplement());
        assertEquals(neighborhoodTest, addressDomain.getNeighborhood());
        assertEquals(cityTest, addressDomain.getCity());
        assertEquals(stateTest, addressDomain.getState());
        assertEquals(countryTest, addressDomain.getCountry());
        assertEquals(postalCodeTest, addressDomain.getPostalCode());
        assertEquals(createdAtTest, addressDomain.getCreatedAt());
        assertEquals(updatedAtTest, addressDomain.getUpdatedAt());
        assertEquals(userTest, addressDomain.getUser());
    }

    @DisplayName("Deve gerar as datas com AddressDomain")
    @Test
    void shouldBeGenerateDatesInAddressDomain() {
        createdAtTest = null;
        updatedAtTest = null;
        var addressDomain = createAddressDomain();

        addressDomain.createDateAddressSave();

        assertNotNull(addressDomain.getCreatedAt());
        assertNotNull(addressDomain.getUpdatedAt());
    }

    @DisplayName("Deve atualizar os dados do AddressDomain com outro AddressDomain")
    @Test
    void shouldBeUpdateAddressDomainValuesWithOtherAddressDomain() {
        var addressDomain = createAddressDomain();
        streetTest = "Rua Bom Sucesso";
        numberTest = "2000";
        complementTest = "Casa do fundo";
        neighborhoodTest = "Fazendinha";
        cityTest = "Belo Horizonte";
        stateTest = "MG";
        countryTest = "Brazil";
        postalCodeTest = "30250-420";
        var otherAddressDomain = createAddressDomain();

        addressDomain.updateAddress(otherAddressDomain);

        assertEquals(streetTest, addressDomain.getStreet());
        assertEquals(numberTest, addressDomain.getNumber());
        assertEquals(complementTest, addressDomain.getComplement());
        assertEquals(neighborhoodTest, addressDomain.getNeighborhood());
        assertEquals(cityTest, addressDomain.getCity());
        assertEquals(stateTest, addressDomain.getState());
        assertEquals(countryTest, addressDomain.getCountry());
        assertEquals(postalCodeTest, addressDomain.getPostalCode());
    }

    private AddressDomain createAddressDomain() {
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

}
