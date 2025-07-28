package com.fiap.tech_challenge.core.domain.usecases.address.impl;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.usecases.address.CreateAddressCase;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateAddressCaseImpl implements CreateAddressCase {
    private final AddressGateway addressGateway;
    private final UserGateway userGateway;

    public CreateAddressCaseImpl(AddressGateway addressGateway, UserGateway userGateway) {
        this.addressGateway = addressGateway;
        this.userGateway = userGateway;
    }

    @Override
    public AddressResponseDto run(String id, AddressDomain addressDomain) {
        var user = userGateway.searchUserById(id);

        log.info("Saving address for user: {}", id);
        addressDomain.createDateAddressSave();
        addressDomain.setUserInAddress(user);

        return addressGateway.createAddress(addressDomain);
    }
}
