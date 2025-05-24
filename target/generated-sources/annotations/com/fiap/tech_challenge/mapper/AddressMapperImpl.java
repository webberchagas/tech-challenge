package com.fiap.tech_challenge.mapper;

import com.fiap.tech_challenge.controller.dto.AddressRequestDto;
import com.fiap.tech_challenge.controller.dto.AddressResponseDto;
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
    date = "2025-05-23T21:30:28-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Arch Linux)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDomain fromRequestToAddressDomain(AddressRequestDto address) {
        if ( address == null ) {
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

        street = address.getStreet();
        number = address.getNumber();
        complement = address.getComplement();
        neighborhood = address.getNeighborhood();
        city = address.getCity();
        state = address.getState();
        country = address.getCountry();
        postalCode = address.getPostalCode();

        String addressId = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        UserDomain user = null;

        AddressDomain addressDomain = new AddressDomain( addressId, street, number, complement, neighborhood, city, state, country, postalCode, createdAt, updatedAt, user );

        return addressDomain;
    }

    @Override
    public AddressEntity toAddressEntity(AddressDomain address) {
        if ( address == null ) {
            return null;
        }

        AddressEntity.AddressEntityBuilder addressEntity = AddressEntity.builder();

        addressEntity.addressId( address.getAddressId() );
        addressEntity.street( address.getStreet() );
        addressEntity.complement( address.getComplement() );
        addressEntity.number( address.getNumber() );
        addressEntity.neighborhood( address.getNeighborhood() );
        addressEntity.city( address.getCity() );
        addressEntity.state( address.getState() );
        addressEntity.country( address.getCountry() );
        addressEntity.postalCode( address.getPostalCode() );
        addressEntity.createdAt( address.getCreatedAt() );
        addressEntity.updatedAt( address.getUpdatedAt() );
        addressEntity.user( userDomainToUserEntity( address.getUser() ) );

        return addressEntity.build();
    }

    @Override
    public AddressResponseDto toAddressResponse(AddressDomain domain) {
        if ( domain == null ) {
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

        addressId = domain.getAddressId();
        street = domain.getStreet();
        number = domain.getNumber();
        complement = domain.getComplement();
        neighborhood = domain.getNeighborhood();
        city = domain.getCity();
        state = domain.getState();
        country = domain.getCountry();
        postalCode = domain.getPostalCode();

        AddressResponseDto addressResponseDto = new AddressResponseDto( addressId, street, number, complement, neighborhood, city, state, country, postalCode );

        return addressResponseDto;
    }

    @Override
    public List<AddressResponseDto> toAddressResponseList(List<AddressDomain> domain) {
        if ( domain == null ) {
            return null;
        }

        List<AddressResponseDto> list = new ArrayList<AddressResponseDto>( domain.size() );
        for ( AddressDomain addressDomain : domain ) {
            list.add( toAddressResponse( addressDomain ) );
        }

        return list;
    }

    @Override
    public List<AddressDomain> toAddressDomainList(List<AddressEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<AddressDomain> list = new ArrayList<AddressDomain>( entity.size() );
        for ( AddressEntity addressEntity : entity ) {
            list.add( toAddressDomain( addressEntity ) );
        }

        return list;
    }

    @Override
    public AddressDomain toAddressDomain(AddressEntity entity) {
        if ( entity == null ) {
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
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        addressId = entity.getAddressId();
        street = entity.getStreet();
        number = entity.getNumber();
        complement = entity.getComplement();
        neighborhood = entity.getNeighborhood();
        city = entity.getCity();
        state = entity.getState();
        country = entity.getCountry();
        postalCode = entity.getPostalCode();
        createdAt = entity.getCreatedAt();
        updatedAt = entity.getUpdatedAt();

        UserDomain user = null;

        AddressDomain addressDomain = new AddressDomain( addressId, street, number, complement, neighborhood, city, state, country, postalCode, createdAt, updatedAt, user );

        return addressDomain;
    }

    protected List<AddressEntity> addressDomainListToAddressEntityList(List<AddressDomain> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressEntity> list1 = new ArrayList<AddressEntity>( list.size() );
        for ( AddressDomain addressDomain : list ) {
            list1.add( toAddressEntity( addressDomain ) );
        }

        return list1;
    }

    protected UserEntity userDomainToUserEntity(UserDomain userDomain) {
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
}
