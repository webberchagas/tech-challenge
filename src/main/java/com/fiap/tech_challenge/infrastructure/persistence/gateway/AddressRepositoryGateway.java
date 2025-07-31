package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.core.exception.NotFoundException;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserAddressEntity;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.AddressMapper;
import com.fiap.tech_challenge.infrastructure.persistence.repository.UserAddressRepository;
import org.springframework.stereotype.Component;

@Component
public class AddressRepositoryGateway implements AddressGateway {
    private final UserAddressRepository userAddressRepository;
    private final AddressMapper addressMapper;

    public AddressRepositoryGateway(UserAddressRepository userAddressRepository, AddressMapper addressMapper) {
        this.userAddressRepository = userAddressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressResponseDto createAddress(AddressDomain addressDomain) {
        var addressEntity = addressMapper.toAddressEntity(addressDomain);
        userAddressRepository.save(addressEntity);
        return addressMapper.toAddressResponse(addressDomain);
    }

    @Override
    public void deleteAddressById(String addressId) {
        var address = userAddressRepository.findById(addressId).orElseThrow(
                () -> new NotFoundException("Address not found with ID: " + addressId)
        );
        userAddressRepository.delete(address);
    }

    @Override
    public UserAddressEntity searchAddressById(String id) {
        return userAddressRepository.findById(id).orElseThrow(() -> new NotFoundException("Address not found with ID: " + id));
    }


}
