package com.fiap.tech_challenge.core.domain.usecases.address.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.usecases.address.ReadAddressCase;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.AddressMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReadAddressCaseImplTest {

    @Mock
    private UserGateway userGateway;
    @Mock
    private AddressMapper addressMapper;

    private ReadAddressCase readAddressCase;

    private AutoCloseable mock;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        readAddressCase = new ReadAddressCaseImpl(userGateway, addressMapper);
    }

    @DisplayName("Deve dar certo")
    @Test
    void shouldBeTrue () {
        assertTrue(true);
    }

}
