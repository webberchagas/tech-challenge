package com.fiap.tech_challenge.core.domain.usecases.address;

import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;

import java.util.List;

public interface ReadAddressByUserIdCase {

    List<AddressResponseDto> run(String userId);
}
