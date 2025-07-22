package com.fiap.tech_challenge.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Request body for creating or updating an address")
public class AddressRequestDto {

    @NotEmpty
    @Schema(description = "Name of the street or avenue", example = "Avenida Paulista")
    private String street;

    @NotEmpty
    @Schema(description = "Address number", example = "2030")
    private String number;

    @Schema(description = "Additional address details (e.g. apartment, suite)", example = "Apt 101")
    private String complement;

    @NotEmpty
    @Schema(description = "Neighborhood", example = "Jardins")
    private String neighborhood;

    @NotEmpty
    @Schema(description = "City", example = "São Paulo")
    private String city;

    @NotEmpty
    @Schema(description = "State abbreviation", example = "SP")
    private String state;

    @NotEmpty
    @Schema(description = "Country", example = "Brasil")
    private String country;

    @NotEmpty
    @Schema(description = "Postal code (CEP)", example = "01311000")
    private String postalCode;

}
