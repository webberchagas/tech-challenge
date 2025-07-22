package com.fiap.tech_challenge.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fiap.tech_challenge.core.domain.model.type.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Request body for updating user information")
public class UserUpdateRequestDto {

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

    @NotNull
    @Schema(description = "User type", example = "RESTAURANT_OWNER")
    private UserType userType;

}

