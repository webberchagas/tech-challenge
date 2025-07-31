package com.fiap.tech_challenge.core.dto.restaurant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.tech_challenge.core.dto.address.AddressRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Request body for creating a new restaurant")
public class RestaurantRequestDto {

    @NotEmpty
    @Schema(description = "Name of the restaurant", example = "Bella Italia")
    private String restaurantName;

    @NotEmpty
    @Pattern(regexp = "^\\d{14}$", message = "CNPJ provided is not valid")
    @Schema(description = "Restaurant CNPJ (14 digits)", example = "12345678000190")
    private String cnpj;

    @NotEmpty
    @Schema(description = "Type of cuisine served", example = "Italian")
    private String cuisineType;

    @NotEmpty
    @Schema(description = "Opening hours by day of the week", example = "Seg a Sex das 18h às 22h, Sáb e Dom 17h às 23h")
    private String openingHours;

    @NotNull
    @Schema(description = "Address of the restaurant")
    private AddressRequestDto address;

    @NotEmpty
    @Schema(description = "User Id for responsible for the restaurant")
    private String userId;
}
