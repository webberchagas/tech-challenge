package com.fiap.tech_challenge.core.domain.usecases.address.impl;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.usecases.address.UpdateAddressCase;
import com.fiap.tech_challenge.core.dto.AddressResponseDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateAddressCaseImpl implements UpdateAddressCase {

    private final AddressGateway addressGateway;

    public UpdateAddressCaseImpl(AddressGateway addressGateway) {
        this.addressGateway = addressGateway;
    }

    public AddressResponseDto run(String id, AddressDomain addressDomain){
        log.info("Searching for address by ID: {} for update", id);
        var addressData = addressGateway.searchAddressById(id);

        log.info("Updating address with ID: {}", addressData.getAddressId());
        addressData.updateAddress(addressData);
        addressData.setUserInAddress(addressData.getUser());

        return addressGateway.createAddress(addressData);
    }
}
