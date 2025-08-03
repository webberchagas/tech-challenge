package com.fiap.tech_challenge.core.domain.usecases.address.impl;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.usecases.address.UpdateAddressCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateAddressCaseImpl implements UpdateAddressCase {

    private final AddressGateway addressGateway;

    public UpdateAddressCaseImpl(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    public AddressDomain run(String id, AddressDomain addressDomain){
        log.info("Searching for address by ID: {} for update", id);
        var addressDataBase = addressGateway.findAddressById(id);

        log.info("Updating address with ID: {}", addressDataBase.getAddressId());
        addressDataBase.updateAddress(addressDomain);

        return addressGateway.createAddress(addressDataBase);
    }
}
