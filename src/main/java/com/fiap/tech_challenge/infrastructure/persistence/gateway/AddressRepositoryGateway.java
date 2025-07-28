package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.entity.AddressEntity;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.AddressMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.AddressRepository;
import com.fiap.tech_challenge.infrastructure.persistence.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class AddressRepositoryGateway implements AddressGateway {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressRepositoryGateway(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressResponseDto createAddress(AddressDomain addressDomain) {
        var addressEntity = addressMapper.toAddressEntity(addressDomain);
        addressRepository.save(addressEntity);
        return addressMapper.toAddressResponse(addressDomain);
    }

    @Override
    public void deleteAddressById(String addressId) {
        var address = addressRepository.findById(addressId).orElseThrow(
                () -> new NotFoundException("Address not found with ID: " + addressId)
        );
        addressRepository.delete(address);
    }

    @Override
    public AddressEntity searchAddressById(String id) {
        var addressEntity = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Address not found with ID: " + id));
        return addressEntity;
    }


}
