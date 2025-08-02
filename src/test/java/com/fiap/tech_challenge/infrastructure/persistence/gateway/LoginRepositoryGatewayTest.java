package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.LoginGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserAddressEntity;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserEntity;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.UserMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginRepositoryGatewayTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    private LoginGateway loginGateway;

    private AutoCloseable mock;

    private String userIdTest;
    private String nameTest;
    private String documentNumberTest;
    private String phoneTest;
    private String emailTest;
    private String passwordTest;
    private UserType userTypeTest;
    private LocalDateTime createdAtTest;
    private LocalDateTime updatedAtTest;
    private List<UserAddressEntity> entityAddressTest;
    private List<AddressDomain> domainAddressTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        loginGateway = new LoginRepositoryGateway(userRepository, userMapper);

        userIdTest = UUID.randomUUID().toString();
        nameTest = "John";
        documentNumberTest = "12345678900";
        phoneTest = "99999999999";
        emailTest = "john@email.com";
        passwordTest = "";
        userTypeTest = UserType.CLIENT;
        createdAtTest = LocalDateTime.now();
        updatedAtTest = LocalDateTime.now();
        entityAddressTest = new ArrayList<>();
        domainAddressTest = new ArrayList<>();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @DisplayName("Deve retornar o usuário pelo e-mail")
    @Test
    void shouldBeFindUserByEmail () {
        var userDomain = createUserDomain();
        when(userMapper.toDomain(any(UserEntity.class))).thenReturn(userDomain);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(createUserEntity()));

        var returnedUserDomain = loginGateway.getUserByEmail(emailTest);

        assertEquals(userDomain, returnedUserDomain);
        verify(userRepository, times(1)).findByEmail(emailTest);
    }

    @DisplayName("Deve lançar exceção de NotFoundException quando não encontrar o usuário")
    @Test
    void shouldBeThrowsNotFoundExceptionWhenNotFindUserByEmail () {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> loginGateway.getUserByEmail(emailTest));

        verify(userRepository, times(1)).findByEmail(emailTest);
    }

    @DisplayName("Deve atualizar a senha do usuário")
    @Test
    void shouldBeUpdateUserPassword () {
        when(userRepository.save(any())).thenAnswer(i -> i.getArgument(0));
        when(userMapper.toEntity(any())).thenReturn(createUserEntity());

        loginGateway.updatedPassword(createUserDomain());

        verify(userRepository, times(1)).save(any());
        verify(userMapper, times(1)).toEntity(any());
    }

    private UserEntity createUserEntity () {
        return new UserEntity(
                userIdTest,
                nameTest,
                documentNumberTest,
                phoneTest,
                emailTest,
                passwordTest,
                userTypeTest,
                createdAtTest,
                updatedAtTest,
                entityAddressTest
        );
    }

    private UserDomain createUserDomain() {
        return new UserDomain(
                userIdTest,
                nameTest,
                documentNumberTest,
                phoneTest,
                emailTest,
                passwordTest,
                createdAtTest,
                updatedAtTest,
                userTypeTest,
                domainAddressTest
        );
    }

}
