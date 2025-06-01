package com.fiap.tech_challenge.mapper;

import com.fiap.tech_challenge.controller.dto.AddressRequestDto;
import com.fiap.tech_challenge.controller.dto.AddressResponseDto;
import com.fiap.tech_challenge.entity.AddressEntity;
import com.fiap.tech_challenge.domain.AddressDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDomain fromRequestToAddressDomain(AddressRequestDto address);
    AddressEntity toAddressEntity(AddressDomain address);
    AddressResponseDto toAddressResponse(AddressDomain domain);
    List<AddressResponseDto> toAddressResponseList(List<AddressDomain> domain);
    List<AddressDomain> toAddressDomainList(List<AddressEntity> entity);

    @Mapping(target = "user", ignore = true)
    AddressDomain toAddressDomain(AddressEntity entity);


}
