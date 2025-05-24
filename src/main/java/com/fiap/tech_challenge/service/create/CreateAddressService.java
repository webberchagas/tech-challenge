package com.fiap.tech_challenge.service.create;

import com.fiap.tech_challenge.exceptions.NotFoundException;
import com.fiap.tech_challenge.mapper.AddressMapper;
import com.fiap.tech_challenge.model.UserEntity;
import com.fiap.tech_challenge.repository.AddressRepository;
import com.fiap.tech_challenge.repository.UserRepository;
import com.fiap.tech_challenge.service.domain.AddressDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateAddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;


    public void createAddress(String userId, AddressDomain address) {
        var userEntity = validateUserExist(userId);

        log.info("Saving address for user: {}", userId);
        address.createDateAddressSave();
        var addressEntity = addressMapper.toAddressEntity(address);
        addressEntity.setUserInAddress(userEntity);
        addressRepository.save(addressEntity);
    }

    private UserEntity validateUserExist(String userId) {
        log.info("Consulting user by ID: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));

    }
}
