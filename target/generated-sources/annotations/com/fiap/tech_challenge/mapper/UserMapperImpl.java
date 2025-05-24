package com.fiap.tech_challenge.mapper;

import com.fiap.tech_challenge.controller.dto.AddressRequestDto;
import com.fiap.tech_challenge.controller.dto.AddressResponseDto;
import com.fiap.tech_challenge.controller.dto.UserCreationRequestDto;
import com.fiap.tech_challenge.controller.dto.UserResponseDto;
import com.fiap.tech_challenge.controller.dto.UserUpdateRequestDto;
import com.fiap.tech_challenge.controller.type.UserType;
import com.fiap.tech_challenge.model.AddressEntity;
import com.fiap.tech_challenge.model.UserEntity;
import com.fiap.tech_challenge.service.domain.AddressDomain;
import com.fiap.tech_challenge.service.domain.UserDomain;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-23T22:01:38-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Arch Linux)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDomain toDomain(UserCreationRequestDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        List<AddressDomain> address = null;
        String name = null;
        String email = null;
        String documentNumber = null;
        String phone = null;
        String password = null;
        UserType userType = null;

        address = addressRequestDtoListToAddressDomainList( userDto.getAddress() );
        name = userDto.getName();
        email = userDto.getEmail();
        documentNumber = userDto.getDocumentNumber();
        phone = userDto.getPhone();
        password = userDto.getPassword();
        userType = userDto.getUserType();

        String userId = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        UserDomain userDomain = new UserDomain( userId, name, email, documentNumber, phone, password, createdAt, updatedAt, userType, address );

        return userDomain;
    }

    @Override
    public UserDomain toDomainUpdate(UserUpdateRequestDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        String name = null;
        String email = null;
        String documentNumber = null;
        String phone = null;
        UserType userType = null;

        name = userDto.getName();
        email = userDto.getEmail();
        documentNumber = userDto.getDocumentNumber();
        phone = userDto.getPhone();
        userType = userDto.getUserType();

        String userId = null;
        String password = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        List<AddressDomain> address = null;

        UserDomain userDomain = new UserDomain( userId, name, email, documentNumber, phone, password, createdAt, updatedAt, userType, address );

        return userDomain;
    }

    @Override
    public UserEntity toEntity(UserDomain userDomain) {
        if ( userDomain == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.userId( userDomain.getUserId() );
        userEntity.name( userDomain.getName() );
        userEntity.documentNumber( userDomain.getDocumentNumber() );
        userEntity.phone( userDomain.getPhone() );
        userEntity.email( userDomain.getEmail() );
        userEntity.password( userDomain.getPassword() );
        userEntity.userType( userDomain.getUserType() );
        userEntity.createdAt( userDomain.getCreatedAt() );
        userEntity.updatedAt( userDomain.getUpdatedAt() );
        userEntity.address( addressDomainListToAddressEntityList( userDomain.getAddress() ) );

        return userEntity.build();
    }

    @Override
    public UserResponseDto toResponseDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        List<AddressResponseDto> address = null;
        String userId = null;
        String name = null;
        String documentNumber = null;
        String email = null;
        String phone = null;
        String password = null;
        UserType userType = null;

        address = addressEntityListToAddressResponseDtoList( userEntity.getAddress() );
        userId = userEntity.getUserId();
        name = userEntity.getName();
        documentNumber = userEntity.getDocumentNumber();
        email = userEntity.getEmail();
        phone = userEntity.getPhone();
        password = userEntity.getPassword();
        userType = userEntity.getUserType();

        UserResponseDto userResponseDto = new UserResponseDto( userId, name, documentNumber, email, phone, password, userType, address );

        return userResponseDto;
    }

    @Override
    public UserDomain fromEntityToDomain(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        String userId = null;
        String name = null;
        String email = null;
        String documentNumber = null;
        String phone = null;
        String password = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        UserType userType = null;

        userId = userEntity.getUserId();
        name = userEntity.getName();
        email = userEntity.getEmail();
        documentNumber = userEntity.getDocumentNumber();
        phone = userEntity.getPhone();
        password = userEntity.getPassword();
        createdAt = userEntity.getCreatedAt();
        updatedAt = userEntity.getUpdatedAt();
        userType = userEntity.getUserType();

        List<AddressDomain> address = null;

        UserDomain userDomain = new UserDomain( userId, name, email, documentNumber, phone, password, createdAt, updatedAt, userType, address );

        return userDomain;
    }

    protected AddressDomain addressRequestDtoToAddressDomain(AddressRequestDto addressRequestDto) {
        if ( addressRequestDto == null ) {
            return null;
        }

        String street = null;
        String number = null;
        String complement = null;
        String neighborhood = null;
        String city = null;
        String state = null;
        String country = null;
        String postalCode = null;

        street = addressRequestDto.getStreet();
        number = addressRequestDto.getNumber();
        complement = addressRequestDto.getComplement();
        neighborhood = addressRequestDto.getNeighborhood();
        city = addressRequestDto.getCity();
        state = addressRequestDto.getState();
        country = addressRequestDto.getCountry();
        postalCode = addressRequestDto.getPostalCode();

        String addressId = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        UserDomain user = null;

        AddressDomain addressDomain = new AddressDomain( addressId, street, number, complement, neighborhood, city, state, country, postalCode, createdAt, updatedAt, user );

        return addressDomain;
    }

    protected List<AddressDomain> addressRequestDtoListToAddressDomainList(List<AddressRequestDto> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressDomain> list1 = new ArrayList<AddressDomain>( list.size() );
        for ( AddressRequestDto addressRequestDto : list ) {
            list1.add( addressRequestDtoToAddressDomain( addressRequestDto ) );
        }

        return list1;
    }

    protected AddressEntity addressDomainToAddressEntity(AddressDomain addressDomain) {
        if ( addressDomain == null ) {
            return null;
        }

        AddressEntity.AddressEntityBuilder addressEntity = AddressEntity.builder();

        addressEntity.addressId( addressDomain.getAddressId() );
        addressEntity.street( addressDomain.getStreet() );
        addressEntity.complement( addressDomain.getComplement() );
        addressEntity.number( addressDomain.getNumber() );
        addressEntity.neighborhood( addressDomain.getNeighborhood() );
        addressEntity.city( addressDomain.getCity() );
        addressEntity.state( addressDomain.getState() );
        addressEntity.country( addressDomain.getCountry() );
        addressEntity.postalCode( addressDomain.getPostalCode() );
        addressEntity.createdAt( addressDomain.getCreatedAt() );
        addressEntity.updatedAt( addressDomain.getUpdatedAt() );
        addressEntity.user( toEntity( addressDomain.getUser() ) );

        return addressEntity.build();
    }

    protected List<AddressEntity> addressDomainListToAddressEntityList(List<AddressDomain> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressEntity> list1 = new ArrayList<AddressEntity>( list.size() );
        for ( AddressDomain addressDomain : list ) {
            list1.add( addressDomainToAddressEntity( addressDomain ) );
        }

        return list1;
    }

    protected AddressResponseDto addressEntityToAddressResponseDto(AddressEntity addressEntity) {
        if ( addressEntity == null ) {
            return null;
        }

        String addressId = null;
        String street = null;
        String number = null;
        String complement = null;
        String neighborhood = null;
        String city = null;
        String state = null;
        String country = null;
        String postalCode = null;

        addressId = addressEntity.getAddressId();
        street = addressEntity.getStreet();
        number = addressEntity.getNumber();
        complement = addressEntity.getComplement();
        neighborhood = addressEntity.getNeighborhood();
        city = addressEntity.getCity();
        state = addressEntity.getState();
        country = addressEntity.getCountry();
        postalCode = addressEntity.getPostalCode();

        AddressResponseDto addressResponseDto = new AddressResponseDto( addressId, street, number, complement, neighborhood, city, state, country, postalCode );

        return addressResponseDto;
    }

    protected List<AddressResponseDto> addressEntityListToAddressResponseDtoList(List<AddressEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressResponseDto> list1 = new ArrayList<AddressResponseDto>( list.size() );
        for ( AddressEntity addressEntity : list ) {
            list1.add( addressEntityToAddressResponseDto( addressEntity ) );
        }

        return list1;
    }
}
