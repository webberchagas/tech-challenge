package com.fiap.tech_challenge.service;

import com.fiap.tech_challenge.exceptions.UserNotFoundException;
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
                .orElseThrow(() -> new UserNotFoundException("Address not found with ID: " + addressId));
        addressRepository.delete(user);
    }
}
