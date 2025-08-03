package com.fiap.tech_challenge.core.domain.usecases.address;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;


public interface CreateAddressCase {

    AddressDomain run(String id, AddressDomain addressDomain);
}
