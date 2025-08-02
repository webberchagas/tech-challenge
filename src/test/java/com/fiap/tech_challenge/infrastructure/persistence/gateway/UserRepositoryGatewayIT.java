package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.exception.AlreadyRegisteredException;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserAddressEntity;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserEntity;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.UserMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.RestaurantRepository;
import com.fiap.tech_challenge.infrastructure.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserRepositoryGatewayIT {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    private UserGateway userGateway;

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
    private final Integer page = 0;
    private final Integer size = 2;
    private final String sort = "name,asc";
    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setup () {
        userGateway = new UserRepositoryGateway(userRepository, userMapper, restaurantRepository);

        userIdTest = UUID.randomUUID().toString();
        nameTest = "John";
        documentNumberTest = "12345678900";
        phoneTest = "99999999999";
        emailTest = "john@email.com";
        passwordTest = "123456";
        userTypeTest = UserType.CLIENT;
        createdAtTest = LocalDateTime.now();
        updatedAtTest = LocalDateTime.now();
        entityAddressTest = new ArrayList<>();
        domainAddressTest = new ArrayList<>();
    }

    @DisplayName("Não deve fazer nada quando o e-mail não existir")
    @Test
    void shouldBeDoNothingWhenEmailDoesNotExist () {
        userGateway.ensureUserEmailIsNotAlreadyRegistered(emailTest);
    }

    @DisplayName("Deve lançar exceção de AlreadyRegisteredException quando e-mail já existir")
    @Test
    void shouldBeThrowAlreadyRegisteredExceptionWhenEmailExists () {
        emailTest = "clebersilva@email.com";
        assertThrows(AlreadyRegisteredException.class, () -> userGateway.ensureUserEmailIsNotAlreadyRegistered(emailTest));
    }

    @DisplayName("Não deve fazer nada quando o número de documento não existir")
    @Test
    void shouldBeDoNothingWhenDocumentNumberDoesNotExist () {
        userGateway.ensureUserDocumentNumberIsNotAlreadyRegistered(documentNumberTest);
    }

    @DisplayName("Deve lançar exceção de AlreadyRegisteredException quando o número de documento existir")
    @Test
    void shouldBeThrowAlreadyRegisteredExceptionWhenDocumentNumberExists () {
        documentNumberTest = "12345678901";

        assertThrows(
                AlreadyRegisteredException.class,
                () -> userGateway.ensureUserDocumentNumberIsNotAlreadyRegistered(documentNumberTest)
        );
    }

    @DisplayName("Deve retornar o UserResponseDto ao criar um novo usuário")
    @Test
    void shouldBeReturnUserResponseDtoWhenCreateNewUser () {
        createdAtTest = null;
        updatedAtTest = null;
        userIdTest = null;
        var userDomain = createUserDomain();

        var createdUserResponseDto = userGateway.createUser(userDomain);

        assertNotNull(createdUserResponseDto.getUserId());
        assertEquals(userDomain.getUserType(), createdUserResponseDto.getUserType());
        assertEquals(userDomain.getName(), createdUserResponseDto.getName());
        assertEquals(userDomain.getPhone(), createdUserResponseDto.getPhone());
        assertEquals(userDomain.getEmail(), createdUserResponseDto.getEmail());
        assertEquals(userDomain.getDocumentNumber(), createdUserResponseDto.getDocumentNumber());
    }

    @DisplayName("Deve remover um usuário")
    @Test
    void shouldBeRemoveUser () {
        userGateway.deleteUser("05e71b88-8d37-4e7f-a055-f3af8d249939");
    }

    @DisplayName("Deve lançar exceção de NotFoundException quando não encontrar usuário")
    @Test
    void shouldBeThrowNotFoundExceptionWhenUserDoesNotExistInDeleteUser () {
        assertThrows(NotFoundException.class, () -> userGateway.deleteUser(userIdTest));
    }

    @DisplayName("Deve retornar usuário quando buscar por id")
    @Test
    void shouldBeFindUserById () {
        var returnedUserDomain = userGateway.getUserById("05e71b88-8d37-4e7f-a055-f3af8d249939");

        assertEquals("05e71b88-8d37-4e7f-a055-f3af8d249939", returnedUserDomain.getUserId());
        assertEquals(UserType.CLIENT, returnedUserDomain.getUserType());
        assertEquals("Cleber Silva", returnedUserDomain.getName());
        assertEquals("clebersilva@email.com", returnedUserDomain.getEmail());
        assertEquals("11999999999", returnedUserDomain.getPhone());
        assertEquals("12345678901", returnedUserDomain.getDocumentNumber());
    }

    @DisplayName("Deve lançar exceção de NotFoundException quando não encontrar usuário")
    @Test
    void shouldBeThrowNotFoundExceptionWhenUserDoesNotExistInSearchUserById () {
        assertThrows(NotFoundException.class, () -> userGateway.getUserById(userIdTest));
    }

    @DisplayName("Deve retornar usuário quando buscar por id")
    @Test
    void shouldBeGetUserById () {
        var returnedUserResponseDto = userGateway.getUserById("05e71b88-8d37-4e7f-a055-f3af8d249939");

        assertEquals("05e71b88-8d37-4e7f-a055-f3af8d249939", returnedUserResponseDto.getUserId());
        assertEquals(UserType.CLIENT, returnedUserResponseDto.getUserType());
        assertEquals("Cleber Silva", returnedUserResponseDto.getName());
        assertEquals("clebersilva@email.com", returnedUserResponseDto.getEmail());
        assertEquals("11999999999", returnedUserResponseDto.getPhone());
        assertEquals("12345678901", returnedUserResponseDto.getDocumentNumber());
    }

    @DisplayName("Deve lançar exceção de NotFoundException quando não encontrar usuário")
    @Test
    void shouldBeThrowNotFoundExceptionWhenUserDoesNotExistInGetUserById () {
        assertThrows(NotFoundException.class, () -> userGateway.getUserById(userIdTest));
    }

    @DisplayName("Deve retornar uma lista de usuários")
    @Test
    void shouldBeReturnUserList () {
        var returnedUserListPage = userGateway.getAllUsers(page, size, sort);

        assertEquals(2, returnedUserListPage.getTotalElements());
        assertEquals(0, returnedUserListPage.getPage());
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
