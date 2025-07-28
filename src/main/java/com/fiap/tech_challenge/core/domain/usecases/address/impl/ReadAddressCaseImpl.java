package com.fiap.tech_challenge.core.domain.usecases.address.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.usecases.address.ReadAddressCase;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.AddressMapper;
import lombok.extern.slf4j.Slf4j;



@Slf4j
public class ReadAddressCaseImpl implements ReadAddressCase {

    private final UserGateway userGateway;
    private final AddressMapper addressMapper;

    public ReadAddressCaseImpl(UserGateway userGateway, AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
        this.userGateway = userGateway;
    }

//    public List<AddressResponseDto> readAddressByUserId(String userId) {
//        log.info("Searching for address by user ID: {}", userId);
//        var userEntity = userGateway.getUserById(userId);
//        var address = userEntity.getAddress();
//
//        return addressMapper.toAddressResponseList(address);
//    }

//    public AddressDomain readAddressById(String userId) {
//        log.info("Searching for address by ID: {}", userId);
//        return addressRepository.findById(userId)
//                .map(addressMapper::toAddressDomain)
//                .orElseThrow(() -> new NotFoundException("Address not found with user ID: " + userId));
//    }

}
