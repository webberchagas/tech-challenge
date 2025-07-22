package com.fiap.tech_challenge.core.adapters;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.dto.AddressResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressGateway {

    AddressResponseDto createAddress(AddressDomain addressDomain);

   void deleteAddressById(String addressId);

    AddressDomain searchAddressById(String id);



}
