package com.fiap.tech_challenge.repository.mapper;

import com.fiap.tech_challenge.controller.dto.AddressResponseDto;
import com.fiap.tech_challenge.controller.dto.UserResponseDto;
import com.fiap.tech_challenge.controller.type.UserType;
import com.fiap.tech_challenge.model.AddressEntity;
import com.fiap.tech_challenge.model.UserEntity;
import com.fiap.tech_challenge.service.domain.AddressDomain;
import com.fiap.tech_challenge.service.domain.UserDomain;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-14T23:23:42-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Arch Linux)"
)
@Component
public class UserMapperEntityImpl implements UserMapperEntity {

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
        String name = null;
        String documentNumber = null;
        String email = null;
        String phone = null;
        String password = null;
        UserType userType = null;

        address = addressEntityListToAddressResponseDtoList( userEntity.getAddress() );
        name = userEntity.getName();
        documentNumber = userEntity.getDocumentNumber();
        email = userEntity.getEmail();
        phone = userEntity.getPhone();
        password = userEntity.getPassword();
        userType = userEntity.getUserType();

        UserResponseDto userResponseDto = new UserResponseDto( name, documentNumber, email, phone, password, userType, address );

        return userResponseDto;
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
        addressEntity.user( addressDomain.getUser() );

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

        String street = null;
        String number = null;
        String complement = null;
        String neighborhood = null;
        String city = null;
        String state = null;
        String country = null;
        String postalCode = null;

        street = addressEntity.getStreet();
        number = addressEntity.getNumber();
        complement = addressEntity.getComplement();
        neighborhood = addressEntity.getNeighborhood();
        city = addressEntity.getCity();
        state = addressEntity.getState();
        country = addressEntity.getCountry();
        postalCode = addressEntity.getPostalCode();

        AddressResponseDto addressResponseDto = new AddressResponseDto( street, number, complement, neighborhood, city, state, country, postalCode );

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
