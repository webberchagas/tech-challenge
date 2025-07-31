package com.fiap.tech_challenge.core.domain.model;

import com.fiap.tech_challenge.core.domain.model.type.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDomainTest {

    private String userIdTest;
    private String nameTest;
    private String emailTest;
    private String documentNumberTest;
    private String phoneTest;
    private String passwordTest;
    private LocalDateTime createdAtTest;
    private LocalDateTime updatedAtTest;
    private UserType userTypeTest;
    private List<AddressDomain> addressTest;

    @BeforeEach
    void setup () {
        userIdTest = "0ea0d8bb-bc69-4977-b2f4-536c335fdae4";
        nameTest = "John";
        emailTest = "john@email.com";
        documentNumberTest = "12345678900";
        phoneTest = "99999999999";
        passwordTest = "password";
        createdAtTest = LocalDateTime.now();
        updatedAtTest = LocalDateTime.now();
        userTypeTest = UserType.CLIENT;
        addressTest = new ArrayList<>();
        addressTest.add(new AddressDomain(
                "d3aaf5c4-9729-4c37-891a-c851874a2b62",
                "Rua Beija-flor",
                "1000",
                "",
                "São Cristóvão",
                "Itacoatiara",
                "AM",
                "Brasil",
                "69103-312",
                null,
                null,
                null
        ));
    }

    @DisplayName("Deve criar um objeto de UserDomain")
    @Test
    void shouldBeCreateUserDomainObject() {
        userIdTest = null;

        var userDomain = createUserDomain();

        assertNull(userDomain.getUserId());
        assertEquals(nameTest, userDomain.getName());
        assertEquals(emailTest, userDomain.getEmail());
        assertEquals(documentNumberTest, userDomain.getDocumentNumber());
        assertEquals(phoneTest, userDomain.getPhone());
        assertEquals(passwordTest, userDomain.getPassword());
        assertEquals(createdAtTest, userDomain.getCreatedAt());
        assertEquals(updatedAtTest, userDomain.getUpdatedAt());
        assertEquals(userTypeTest, userDomain.getUserType());
        assertEquals(addressTest, userDomain.getAddress());
    }

    @DisplayName("Deve criar um objeto de UserDomain com ID")
    @Test
    void shouldBeCreateUserDomainObjectWithId() {
        var userDomain = createUserDomain();

        assertEquals(userIdTest, userDomain.getUserId());
        assertEquals(nameTest, userDomain.getName());
        assertEquals(emailTest, userDomain.getEmail());
        assertEquals(documentNumberTest, userDomain.getDocumentNumber());
        assertEquals(phoneTest, userDomain.getPhone());
        assertEquals(passwordTest, userDomain.getPassword());
        assertEquals(createdAtTest, userDomain.getCreatedAt());
        assertEquals(updatedAtTest, userDomain.getUpdatedAt());
        assertEquals(userTypeTest, userDomain.getUserType());
        assertEquals(addressTest, userDomain.getAddress());
    }

    @DisplayName("Deve gerar as datas com UserDomain")
    @Test
    void shouldBeGenerateDatesInUserDomain() {
        createdAtTest = null;
        updatedAtTest = null;
        var userDomain = createUserDomain();

        userDomain.createUserSave();

        assertNotNull(userDomain.getCreatedAt());
        assertNotNull(userDomain.getUpdatedAt());
    }

    @DisplayName("Deve atualizar os dados do UserDomain com outro UserDomain")
    @Test
    void shouldBeUpdateUserDomainValuesWithOtherUserDomain() {
        var userDomain = createUserDomain();
        nameTest = "Maria";
        phoneTest = "88888888888";
        emailTest = "maria@email.com";
        userTypeTest = UserType.RESTAURANT_OWNER;
        documentNumberTest = "98765432199";
        var otherUserDomain = createUserDomain();

        userDomain.updateUser(otherUserDomain);

        assertEquals(nameTest, userDomain.getName());
        assertEquals(emailTest, userDomain.getEmail());
        assertEquals(documentNumberTest, userDomain.getDocumentNumber());
        assertEquals(phoneTest, userDomain.getPhone());
        assertEquals(userTypeTest, userDomain.getUserType());
    }

    private UserDomain createUserDomain() {
        return new UserDomain(
                userIdTest,
                nameTest,
                emailTest,
                documentNumberTest,
                phoneTest,
                passwordTest,
                createdAtTest,
                updatedAtTest,
                userTypeTest,
                addressTest
        );
    }

}
