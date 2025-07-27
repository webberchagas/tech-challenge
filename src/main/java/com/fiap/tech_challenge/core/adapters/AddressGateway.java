package com.fiap.tech_challenge.core.adapters;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.infrastructure.persistence.entity.AddressEntity;

public interface AddressGateway {

    AddressResponseDto createAddress(AddressDomain addressDomain);

   void deleteAddressById(String addressId);

    AddressEntity searchAddressById(String id);



}
