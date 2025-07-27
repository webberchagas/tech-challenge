package com.fiap.tech_challenge.core.domain.usecases.address;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;


public interface CreateAddressCase {

    AddressResponseDto run(String id, AddressDomain addressDomain);
}
