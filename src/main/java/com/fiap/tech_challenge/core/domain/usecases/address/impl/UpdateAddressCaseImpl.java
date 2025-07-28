package com.fiap.tech_challenge.core.domain.usecases.address.impl;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.domain.usecases.address.UpdateAddressCase;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.AddressMapper;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateAddressCaseImpl implements UpdateAddressCase {

    private final AddressGateway addressGateway;
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    public UpdateAddressCaseImpl(AddressGateway addressGateway, AddressMapper addressMapper, UserMapper userMapper) {
        this.addressGateway = addressGateway;
        this.addressMapper = addressMapper;
        this.userMapper = userMapper;
    }

    public AddressResponseDto run(String id, AddressDomain addressDomain){
        log.info("Searching for address by ID: {} for update", id);
        var addressDataBase = addressGateway.searchAddressById(id);
        var domainDataBase = addressMapper.toAddressDomain(addressDataBase);

        log.info("Updating address with ID: {}", domainDataBase.getAddressId());
        domainDataBase.updateAddress(addressDomain);
        domainDataBase.setUserInAddress(userMapper.fromEntityToDomain(addressDataBase.getUser()));

        return addressGateway.createAddress(domainDataBase);
    }
}
