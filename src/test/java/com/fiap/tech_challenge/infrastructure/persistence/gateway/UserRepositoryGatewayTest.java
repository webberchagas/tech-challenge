package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import com.fiap.tech_challenge.core.exception.AlreadyRegisteredException;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.entity.AddressEntity;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserEntity;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.UserMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserRepositoryGatewayTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    private UserGateway userGateway;

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
    private List<AddressEntity> entityAddressTest;
    private List<AddressDomain> domainAddressTest;
    private List<AddressResponseDto> responseDtoAddressTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        userGateway = new UserRepositoryGateway(userRepository, userMapper);

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
        responseDtoAddressTest = new ArrayList<>();
    }

    @AfterEach
    void tearDown () throws Exception {
        mock.close();
    }

    @DisplayName("Não deve fazer nada quando o e-mail não existir")
    @Test
    void shouldBeDoNothingWhenEmailDoesNotExist () {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        userGateway.doesUserEmailExists(emailTest);

        verify(userRepository, times(1)).findByEmail(emailTest);
    }

    @DisplayName("Deve lançar exceção de AlreadyRegisteredException quando e-mail já existir")
    @Test
    void shouldBeThrowAlreadyRegisteredExceptionWhenEmailExists () {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(createUserEntity()));

        assertThrows(AlreadyRegisteredException.class, () -> userGateway.doesUserEmailExists(emailTest));

        verify(userRepository, times(1)).findByEmail(emailTest);
    }

    @DisplayName("Não deve fazer nada quando o número de documento não existir")
    @Test
    void shouldBeDoNothingWhenDocumentNumberDoesNotExist () {
        when(userRepository.findByDocumentNumber(anyString())).thenReturn(Optional.empty());

        userGateway.doesUserDocumentNumberExists(documentNumberTest);

        verify(userRepository, times(1)).findByDocumentNumber(documentNumberTest);
    }

    @DisplayName("Deve lançar exceção de AlreadyRegisteredException quando o número de documento existir")
    @Test
    void shouldBeThrowAlreadyRegisteredExceptionWhenDocumentNumberExists () {
        when(userRepository.findByDocumentNumber(anyString())).thenReturn(Optional.of(createUserEntity()));

        assertThrows(
                AlreadyRegisteredException.class,
                () -> userGateway.doesUserDocumentNumberExists(documentNumberTest)
        );

        verify(userRepository, times(1)).findByDocumentNumber(documentNumberTest);
    }

    @DisplayName("Deve retornar o UserResponseDto ao criar um novo usuário")
    @Test
    void shouldBeReturnUserResponseDtoWhenCreateNewUser () {
        var userResponseDto = createUserResponseDto();
        var userEntity = createUserEntity();
        var userDomain = createUserDomain();
        when(userMapper.toEntity(any())).thenReturn(userEntity);
        when(userRepository.save(any())).thenReturn(userEntity);
        when(userMapper.toResponseDto(any())).thenReturn(userResponseDto);

        var createdUserResponseDto = userGateway.createUser(userDomain);

        assertEquals(userResponseDto, createdUserResponseDto);
        verify(userRepository, times(1)).save(userEntity);
        verify(userMapper, times(1)).toEntity(userDomain);
        verify(userMapper, times(1)).toResponseDto(userEntity);
    }

    @DisplayName("Deve remover um usuário")
    @Test
    void shouldBeRemoveUser () {
        var userEntity = createUserEntity();
        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        doNothing().when(userRepository).delete(any());

        userGateway.deleteUser(userIdTest);

        verify(userRepository, times(1)).findById(userIdTest);
        verify(userRepository, times(1)).delete(userEntity);
    }

    @DisplayName("Deve lançar exceção de NotFoundException quando não encontrar usuário")
    @Test
    void shouldBeThrowNotFoundExceptionWhenUserDoesNotExistInDeleteUser () {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userGateway.deleteUser(userIdTest));

        verify(userRepository, times(1)).findById(userIdTest);
    }

    @DisplayName("Deve retornar usuário quando buscar por id")
    @Test
    void shouldBeFindUserById () {
        var userEntity = createUserEntity();
        var userDomain = createUserDomain();
        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        when(userMapper.fromEntityToDomain(any())).thenReturn(userDomain);

        var returnedUserDomain = userGateway.searchUserById(userIdTest);

        assertEquals(userDomain, returnedUserDomain);
        verify(userRepository, times(1)).findById(userIdTest);
        verify(userMapper, times(1)).fromEntityToDomain(userEntity);
    }

    @DisplayName("Deve lançar exceção de NotFoundException quando não encontrar usuário")
    @Test
    void shouldBeThrowNotFoundExceptionWhenUserDoesNotExistInSearchUserById () {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userGateway.searchUserById(userIdTest));

        verify(userRepository, times(1)).findById(userIdTest);
    }

    @DisplayName("Deve retornar usuário quando buscar por id")
    @Test
    void shouldBeGetUserById () {
        var userEntity = createUserEntity();
        var userResponseDto = createUserResponseDto();
        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        when(userMapper.toResponseDto(any())).thenReturn(userResponseDto);

        var returnedUserResponseDto = userGateway.getUserById(userIdTest);

        assertEquals(userResponseDto, returnedUserResponseDto);
        verify(userRepository, times(1)).findById(userIdTest);
        verify(userMapper, times(1)).toResponseDto(userEntity);
    }

    @DisplayName("Deve lançar exceção de NotFoundException quando não encontrar usuário")
    @Test
    void shouldBeThrowNotFoundExceptionWhenUserDoesNotExistInGetUserById () {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userGateway.getUserById(userIdTest));

        verify(userRepository, times(1)).findById(userIdTest);
    }

    @DisplayName("Deve retornar uma lista de usuários")
    @Test
    void shouldBeReturnUserList () {
        var userList = new ArrayList<UserEntity>();
        userList.add(createUserEntity());
        userList.add(createUserEntity());
        userList.add(createUserEntity());
        var userListPage = new PageImpl<>(userList);
        var pageable = PageRequest.of(0, 10);
        when(userRepository.findAll(any(Pageable.class))).thenReturn(userListPage);
        when(userMapper.toResponseDto(any())).thenReturn(createUserResponseDto());

        var returnedUserListPage = userGateway.getAllUsers(pageable);

        assertEquals(userListPage.getTotalElements(), returnedUserListPage.getTotalElements());
        assertEquals(userListPage.getTotalPages(), returnedUserListPage.getTotalPages());
        verify(userRepository, times(1)).findAll(pageable);
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

    private UserResponseDto createUserResponseDto () {
        return new UserResponseDto(
                userIdTest,
                nameTest,
                documentNumberTest,
                emailTest,
                phoneTest,
                userTypeTest,
                responseDtoAddressTest
        );
    }

}
