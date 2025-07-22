package com.fiap.tech_challenge.infrastructure.application.impl;


import com.fiap.tech_challenge.core.domain.usecases.address.CreateAddressCase;
import com.fiap.tech_challenge.core.domain.usecases.address.DeleteAddressCase;
import com.fiap.tech_challenge.core.domain.usecases.address.ReadAddressCase;
import com.fiap.tech_challenge.core.domain.usecases.address.UpdateAddressCase;
import com.fiap.tech_challenge.core.dto.AddressRequestDto;
import com.fiap.tech_challenge.core.dto.AddressResponseDto;
import com.fiap.tech_challenge.infrastructure.application.AddressController;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.AddressMapper;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController {

    private final CreateAddressCase createAddressCase;
    private final DeleteAddressCase deleteAddressCase;
    private final UpdateAddressCase updateAddressCase;
//    private final ReadAddressCase readAddressCase;
    private final AddressMapper addressMapper;

//    @Override
//    public AddressResponseDto getAddressById(String id) {
//        var address = readAddressCase.getUserById(id);
//        return address;
//    }
//
//    @Override
//    public List<AddressResponseDto> getAddressByUserId(String userId) {
//        var address = readAddressCase.readAddressByUserId(userId);
//        return addressMapper.toAddressResponseList(address);
//    }

    @Override
    public AddressResponseDto createAddress(String userId, AddressRequestDto request) {
        var addressDomain = addressMapper.fromRequestToAddressDomain(request);
        var newAddress = createAddressCase.run(userId,addressDomain);
        return newAddress;
    }

    @Override
    public AddressResponseDto updateAddressById(String id, AddressRequestDto request) {
        var addressDomain = addressMapper.fromRequestToAddressDomain(request);
        var newAddress = updateAddressCase.run(id, addressDomain);
        return newAddress;
    }

    @Override
    public void deleteAddressById(String id) {
        deleteAddressCase.run(id);
    }
}
