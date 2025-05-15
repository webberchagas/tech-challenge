package com.fiap.tech_challenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressRequestDto {

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Name of the street or avenue", example = "Avenida Paulista")
    private String street;

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Number of the address", example = "2030")
    private String number;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Complement of the address", example = "Apt 101")
    private String complement;

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Name of the neighborhood", example = "Jardins")
    private String neighborhood;

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "City of the user", example = "São Paulo")
    private String city;

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "State of the user", example = "SP")
    private String state;

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Country of the user", example = "Brazil")
    private String country;

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Postal code of the user", example = "01311000")
    private String postalCode;

}
