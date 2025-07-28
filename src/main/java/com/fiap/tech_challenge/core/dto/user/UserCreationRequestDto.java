package com.fiap.tech_challenge.core.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.dto.address.AddressRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Request body for creating a new user")
public class UserCreationRequestDto {

    @NotEmpty
    @Schema(description = "User full name", example = "John Doe")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^\\d{11}$", message = "CPF provided is not valid")
    @Schema(description = "User CPF (11 digits)", example = "12345678909")
    private String documentNumber;

    @NotEmpty
    @Email(message = "Must be a valid email address")
    @Schema(description = "User email (used for login)", example = "johndoe@email.com")
    private String email;

    @NotEmpty
    @Pattern(regexp = "^\\d{10,11}$", message = "Phone number must have 10 or 11 digits")
    @Schema(description = "User phone number", example = "11999999999")
    private String phone;

    @NotEmpty
    @Size(min = 8, max = 15, message = "Password must be at least 8 characters and maximum 15 characters")
    @Schema(description = "User password (8 to 15 characters)", example = "A123456*")
    private String password;

    @NotNull
    @Schema(description = "User type", example = "RESTAURANT_OWNER")
    private UserType userType;

    @NotNull
    @Schema(description = "List of addresses for the user")
    private List<AddressRequestDto> address;
}

