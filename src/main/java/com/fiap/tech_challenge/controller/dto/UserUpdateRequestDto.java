package com.fiap.tech_challenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.tech_challenge.controller.type.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateRequestDto {

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Name of the user", example = "John Doe")
    private String name;

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "User's CPF", example = "12345678909")
    @Pattern(regexp = "^\\d{11}$", message = "CPF provided is not valid")
    private String documentNumber;

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "User email, this information will be used for login", example = "johndoe@email.com")
    private String email;

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Phone number of the user", example = "11999999999")
    private String phone;


    @NotNull
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "User type", example = "RESTAURANT_OWNER")
    private UserType userType;

}

