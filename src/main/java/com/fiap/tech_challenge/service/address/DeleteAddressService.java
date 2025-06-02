package com.fiap.tech_challenge.service.address;

import com.fiap.tech_challenge.exception.NotFoundException;
import com.fiap.tech_challenge.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteAddressService {

    private final AddressRepository addressRepository;

    public void deleteAddressById(String addressId) {
        log.info("Deleting user by user id: {}", addressId);
        var user = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException("Address not found with ID: " + addressId));
        addressRepository.delete(user);
    }
}
