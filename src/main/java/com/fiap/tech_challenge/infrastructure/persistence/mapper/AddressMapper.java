package com.fiap.tech_challenge.infrastructure.persistence.mapper;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.dto.address.AddressRequestDto;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserAddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDomain fromRequestToAddressDomain(AddressRequestDto address);
    UserAddressEntity toAddressEntity(AddressDomain address);
    AddressResponseDto fromEntitytoResponse(UserAddressEntity entity);
    AddressResponseDto toAddressResponse(AddressDomain domain);
    List<AddressResponseDto> toAddressResponseList(List<AddressDomain> domain);

    @Mapping(target = "user", ignore = true)
    AddressDomain toAddressDomain(UserAddressEntity entity);


}
