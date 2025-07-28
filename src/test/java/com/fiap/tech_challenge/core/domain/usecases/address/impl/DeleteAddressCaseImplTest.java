package com.fiap.tech_challenge.core.domain.usecases.address.impl;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.domain.usecases.address.DeleteAddressCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class DeleteAddressCaseImplTest {

    @Mock
    private AddressGateway addressGateway;

    private DeleteAddressCase deleteAddressCase;

    private AutoCloseable mock;

    private String addressIdTest;

    @BeforeEach
    void setup () {
        mock = MockitoAnnotations.openMocks(this);
        deleteAddressCase = new DeleteAddressCaseImpl(addressGateway);

        addressIdTest = UUID.randomUUID().toString();
    }

    @AfterEach
    void tearDown () throws Exception {
        mock.close();
    }

    @DisplayName("Deve remover o endereço")
    @Test
    void shouldBeRemoveAddress () {
        doNothing().when(addressGateway).deleteAddressById(anyString());

        deleteAddressCase.run(addressIdTest);

        verify(addressGateway, times(1))
                .deleteAddressById(anyString());
    }

}
