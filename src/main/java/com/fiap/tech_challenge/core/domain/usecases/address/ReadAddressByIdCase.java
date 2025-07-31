package com.fiap.tech_challenge.core.domain.usecases.address;

import com.fiap.tech_challenge.infrastructure.persistence.entity.UserAddressEntity;

public interface ReadAddressByIdCase {

    UserAddressEntity run(String addressId);
}
