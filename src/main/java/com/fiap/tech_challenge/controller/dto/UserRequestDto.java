package com.fiap.tech_challenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.tech_challenge.controller.type.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDto {

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Name of the user", example = "John Doe")
    private String name;

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "User's CPF", example = "12345678909")
    @Pattern(regexp = "^[0-9]{11}$", message = "CPF provided is not valid")
    private String documentNumber;

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "User email, this information will be used for login", example = "johndoe@email.com")
    private String email;

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Phone number of the user", example = "11999999999")
    private String phone;

    @NotEmpty
    @Size(min = 8, max = 15, message = "Password must be at least 8 characters and maximum 15 characters")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The password will be used for login. It must have at least 8 characters and a maximum of 15", example = "A123456*")
    private String password;

    @NotNull
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "User type", example = "CLIENT or RESTAURANT_OWNER")
    private UserType userType;

    @NotNull
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Address of the user")
    private List<AddressRequestDto> address;
}

