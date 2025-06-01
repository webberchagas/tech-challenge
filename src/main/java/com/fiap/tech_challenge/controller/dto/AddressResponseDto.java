package com.fiap.tech_challenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Response body containing address information")
public class AddressResponseDto {

    @Schema(description = "Unique identifier of the address", example = "a1b2c3d4")
    private String addressId;

    @Schema(description = "Street or avenue name", example = "Avenida Paulista")
    private String street;

    @Schema(description = "Address number", example = "2030")
    private String number;

    @Schema(description = "Address complement (e.g. apartment, suite)", example = "Apt 101")
    private String complement;

    @Schema(description = "Neighborhood", example = "Jardins")
    private String neighborhood;

    @Schema(description = "City", example = "São Paulo")
    private String city;

    @Schema(description = "State abbreviation", example = "SP")
    private String state;

    @Schema(description = "Country", example = "Brasil")
    private String country;

    @Schema(description = "Postal code (CEP)", example = "01311000")
    private String postalCode;

}
