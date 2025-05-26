package com.fiap.tech_challenge.controller.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangePasswordRequestDto {

    @NotEmpty
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "User email, this information will be used for login", example = "johndoe@email.com")
    private String email;

    @NotEmpty
    @Size(min = 8, max = 15, message = "Password must be at least 8 characters and maximum 15 characters")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The password will be used for login. It must have at least 8 characters and a maximum of 15", example = "A123456*")
    private String newPassword;

    @NotEmpty
    @Size(min = 8, max = 15, message = "Password must be at least 8 characters and maximum 15 characters")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The password will be used for login. It must have at least 8 characters and a maximum of 15", example = "A123456*")
    private String confirmNewPassword;
}
