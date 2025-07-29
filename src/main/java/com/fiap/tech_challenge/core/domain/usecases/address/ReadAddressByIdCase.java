package com.fiap.tech_challenge.core.domain.usecases.address;

import com.fiap.tech_challenge.infrastructure.persistence.entity.AddressEntity;

public interface ReadAddressByIdCase {

    AddressEntity run(String addressId);
}
