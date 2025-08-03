package com.fiap.tech_challenge.infrastructure.persistence.gateway;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.domain.model.AddressDomain;
import com.fiap.tech_challenge.core.exception.NotFoundException;
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
    public AddressDomain createAddress(AddressDomain addressDomain) {
        var addressEntity = addressMapper.toAddressEntity(addressDomain);
        var userAddressEntity = userAddressRepository.save(addressEntity);
        return addressMapper.toAddressDomain(userAddressEntity);
    }

    @Override
    public void deleteAddressById(String addressId) {
        var address = userAddressRepository.findById(addressId).orElseThrow(
                () -> new NotFoundException("Address not found with ID: " + addressId)
        );
        userAddressRepository.delete(address);
    }

    @Override
    public AddressDomain findAddressById(String id) {
         var address = userAddressRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Address not found with ID: " + id));
        return addressMapper.toAddressDomain(address);
    }


}
