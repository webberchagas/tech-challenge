package com.fiap.tech_challenge.controller.mapper;

import com.fiap.tech_challenge.controller.dto.AddressRequestDto;
import com.fiap.tech_challenge.controller.dto.UserRequestDto;
import com.fiap.tech_challenge.controller.type.UserType;
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
    date = "2025-05-14T23:23:42-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Arch Linux)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDomain toDomain(UserRequestDto userDto) {
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

        UserDomain userDomain = new UserDomain( userId, name, email, documentNumber, phone, password, createdAt, updatedAt, address, userType );

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
        UserEntity user = null;

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
}
