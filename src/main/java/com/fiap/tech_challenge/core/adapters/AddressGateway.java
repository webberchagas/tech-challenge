package com.fiap.tech_challenge.core.adapters;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;

public interface AddressGateway {

    AddressDomain createAddress(AddressDomain addressDomain);
    AddressDomain findAddressById(String id);
    void deleteAddressById(String addressId);


}
