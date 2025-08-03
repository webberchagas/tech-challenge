package com.fiap.tech_challenge.core.application.impl;

import com.fiap.tech_challenge.core.dto.address.AddressRequestDto;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.core.application.AddressController;
import com.fiap.tech_challenge.core.domain.usecases.address.*;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.AddressMapper;
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
    private final ReadAddressByIdCase readAddressByIdCase;
    private final ReadAddressByUserIdCase readAddressByUserIdCase;
    private final AddressMapper addressMapper;

    @Override
    public AddressResponseDto getAddressById(String id) {
        var address = readAddressByIdCase.run(id);
        return addressMapper.toAddressResponse(address);
    }

    @Override
    public List<AddressResponseDto> getAddressByUserId(String userId) {
        var address = readAddressByUserIdCase.run(userId);
        return addressMapper.toAddressResponseList(address);
    }

    @Override
    public AddressResponseDto createAddress(String userId, AddressRequestDto request) {
        var addressRequestDomain = addressMapper.fromRequestToAddressDomain(request);
        var addressDomain =  createAddressCase.run(userId, addressRequestDomain);
        return addressMapper.toAddressResponse(addressDomain);
    }

    @Override
    public AddressResponseDto updateAddressById(String id, AddressRequestDto request) {
        var addressRequestDomain = addressMapper.fromRequestToAddressDomain(request);
        var addressDomain = updateAddressCase.run(id, addressRequestDomain);
        return addressMapper.toAddressResponse(addressDomain);
    }

    @Override
    public void deleteAddressById(String id) {
        deleteAddressCase.run(id);
    }
}
