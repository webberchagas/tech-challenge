package com.fiap.tech_challenge.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AddressDomain {
    private String addressId;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDomain user;

    public void createDateAddressSave() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateAddress(AddressDomain addressDomain) {
        this.updatedAt = LocalDateTime.now();
        this.street = addressDomain.getStreet();
        this.number = addressDomain.getNumber();
        this.complement = addressDomain.getComplement();
        this.neighborhood = addressDomain.getNeighborhood();
        this.city = addressDomain.getCity();
        this.state = addressDomain.getState();
        this.country = addressDomain.getCountry();
        this.postalCode = addressDomain.getPostalCode();
    }

    public void setUserInAddress(UserDomain user) {
        this.user = user;
    }

}
