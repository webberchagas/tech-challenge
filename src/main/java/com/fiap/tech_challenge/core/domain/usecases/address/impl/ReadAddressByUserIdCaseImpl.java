package com.fiap.tech_challenge.core.domain.usecases.address.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.usecases.address.ReadAddressByUserIdCase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class ReadAddressByUserIdCaseImpl implements ReadAddressByUserIdCase {

    private final UserGateway userGateway;

    public ReadAddressByUserIdCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<AddressDomain> run(String userId) {
        log.info("Searching for address by user ID: {}", userId);
        var userDomain = userGateway.getUserById(userId);
        return userDomain.getAddress();
    }

}
