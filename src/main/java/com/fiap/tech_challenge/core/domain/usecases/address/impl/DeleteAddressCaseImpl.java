package com.fiap.tech_challenge.core.domain.usecases.address.impl;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.domain.usecases.address.DeleteAddressCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteAddressCaseImpl implements DeleteAddressCase {

    private final AddressGateway addressGateway;

    public DeleteAddressCaseImpl(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    @Override
    public void run(String addressId){
        log.info("Deleting Address by address id: {}", addressId);
        addressGateway.deleteAddressById(addressId);
    }

}
