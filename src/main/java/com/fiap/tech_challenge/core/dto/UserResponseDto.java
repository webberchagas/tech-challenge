package com.fiap.tech_challenge.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fiap.tech_challenge.core.domain.model.type.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Response body containing user information")
public class UserResponseDto {
    @Schema(description = "Unique identifier of the user", example = "u123abc456")
    private String userId;

    @Schema(description = "Full name of the user", example = "John Doe")
    private String name;

    @Schema(description = "User CPF", example = "12345678909")
    private String documentNumber;

    @Schema(description = "User email", example = "john.doe@email.com")
    private String email;

    @Schema(description = "User phone number", example = "11999999999")
    private String phone;

    @Schema(description = "User type", example = "RESTAURANT_OWNER")
    private UserType userType;

    @Schema(description = "List of addresses associated with the user")
    private List<AddressResponseDto> address;

}
