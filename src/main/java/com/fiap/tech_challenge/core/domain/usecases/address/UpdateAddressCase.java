package com.fiap.tech_challenge.core.domain.usecases.address;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;

public interface UpdateAddressCase {

    AddressDomain run(String id, AddressDomain addressDomain);
}
