package com.fiap.tech_challenge.core.domain.usecases.address.impl;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.domain.usecases.address.ReadAddressByIdCase;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserAddressEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadAddressByIdCaseImpl implements ReadAddressByIdCase {

    private final AddressGateway addressGateway;

    public ReadAddressByIdCaseImpl(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    @Override
    public UserAddressEntity run(String addressId) {
        log.info("Searching for address by ID: {}", addressId);
        return addressGateway.searchAddressById(addressId);
    }
}
