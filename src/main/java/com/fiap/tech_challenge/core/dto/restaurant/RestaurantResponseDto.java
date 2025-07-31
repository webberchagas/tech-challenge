package com.fiap.tech_challenge.core.dto.restaurant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.tech_challenge.core.dto.address.AddressResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Response body containing restaurant information")
public class RestaurantResponseDto {

    @Schema(description = "Unique identifier of the restaurant", example = "r123abc456")
    private String restaurantId;

    @Schema(description = "Name of the restaurant", example = "Bella Italia")
    private String restaurantName;

    @Schema(description = "CNPJ of the restaurant", example = "12345678000195")
    private String cnpj;

    @Schema(description = "Type of cuisine served", example = "Italian")
    private String cuisineType;

    @Schema(description = "Opening hours by day of the week")
    private String openingHours;

    @Schema(description = "Address of the restaurant")
    private AddressResponseDto address;

    @Schema(description = "User responsible for the restaurant")
    private UserResponseDto user;
}
