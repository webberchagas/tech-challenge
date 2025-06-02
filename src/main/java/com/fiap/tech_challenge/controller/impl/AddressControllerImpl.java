package com.fiap.tech_challenge.controller.impl;

import com.fiap.tech_challenge.controller.AddressController;
import com.fiap.tech_challenge.controller.dto.AddressRequestDto;
import com.fiap.tech_challenge.controller.dto.AddressResponseDto;
import com.fiap.tech_challenge.mapper.AddressMapper;
import com.fiap.tech_challenge.service.address.CreateAddressService;
import com.fiap.tech_challenge.service.address.DeleteAddressService;
import com.fiap.tech_challenge.service.address.ReadAddressService;
import com.fiap.tech_challenge.service.address.UpdateAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController {

    private final DeleteAddressService deleteAddressService;
    private final UpdateAddressService updateAddressService;
    private final CreateAddressService createAddressService;
    private final ReadAddressService readAddressService;
    private final AddressMapper addressMapper;

    @Override
    public AddressResponseDto getAddressById(String id) {
        var address = readAddressService.readAddressById(id);
        return addressMapper.toAddressResponse(address);
    }

    @Override
    public List<AddressResponseDto> getAddressByUserId(String userId) {
        var address = readAddressService.readAddressByUserId(userId);
        return addressMapper.toAddressResponseList(address);
    }

    @Override
    public AddressResponseDto createAddress(String userId, AddressRequestDto request) {
        var addressDate = addressMapper.fromRequestToAddressDomain(request);
        var addressDomain = createAddressService.createAddress(userId, addressDate);
        return addressMapper.toAddressResponse(addressDomain);
    }

    @Override
    public AddressResponseDto updateAddressById(String id, AddressRequestDto request) {
        var userDomain = addressMapper.fromRequestToAddressDomain(request);
        var addressDomain = updateAddressService.updateAddressById(id, userDomain);
        return addressMapper.toAddressResponse(addressDomain);
    }

    @Override
    public void deleteAddressById(String id) {
        deleteAddressService.deleteAddressById(id);
    }
}
