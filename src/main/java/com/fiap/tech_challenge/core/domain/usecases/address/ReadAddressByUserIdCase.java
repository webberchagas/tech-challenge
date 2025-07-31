package com.fiap.tech_challenge.core.domain.usecases.address;

import com.fiap.tech_challenge.core.domain.model.AddressDomain;

import java.util.List;

public interface ReadAddressByUserIdCase {

    List<AddressDomain> run(String userId);
}
