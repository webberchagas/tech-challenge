package com.fiap.tech_challenge.service.update;

import com.fiap.tech_challenge.exceptions.NotFoundException;
import com.fiap.tech_challenge.mapper.AddressMapper;
import com.fiap.tech_challenge.model.AddressEntity;
import com.fiap.tech_challenge.repository.AddressRepository;
import com.fiap.tech_challenge.service.domain.AddressDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateAddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public void updateAddressById(String id, AddressDomain newAddressData) {
        var addressDataBase = findAddressById(id);
        log.info("Updating address with ID: {}", addressDataBase.getAddressId());

        var domainDataBase = addressMapper.toAddressDomain(addressDataBase);
        domainDataBase.updateAddress(newAddressData);

        var addressToUpdate = addressMapper.toAddressEntity(domainDataBase);
        addressToUpdate.setUserInAddress(addressDataBase.getUser());

        addressRepository.save(addressToUpdate);
    }

    private AddressEntity findAddressById(String id) {
        log.info("Searching for address by ID: {} for update", id);
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found with ID: " + id));
    }

}
