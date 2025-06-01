package com.fiap.tech_challenge.service.address;

import com.fiap.tech_challenge.exception.NotFoundException;
import com.fiap.tech_challenge.mapper.AddressMapper;
import com.fiap.tech_challenge.repository.AddressRepository;
import com.fiap.tech_challenge.repository.UserRepository;
import com.fiap.tech_challenge.domain.AddressDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadAddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    public List<AddressDomain> readAddressByUserId(String userId) {
        log.info("Searching for address by user ID: {}", userId);
        var userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Address not found with user ID: " + userId));
        var address = userEntity.getAddress();

        return addressMapper.toAddressDomainList(address);
    }

    public AddressDomain readAddressById(String userId) {
        log.info("Searching for address by ID: {}", userId);
        return addressRepository.findById(userId)
                .map(addressMapper::toAddressDomain)
                .orElseThrow(() -> new NotFoundException("Address not found with user ID: " + userId));
    }
}
