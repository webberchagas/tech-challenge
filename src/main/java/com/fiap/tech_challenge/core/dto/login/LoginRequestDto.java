package com.fiap.tech_challenge.core.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Request body for user login")
public class LoginRequestDto {

    @NotEmpty
    @Email(message = "Must be a valid email address")
    @Schema(description = "User email", example = "johndoe@email.com")
    private String email;

    @NotEmpty
    @Schema(description = "User password", example = "A1234567")
    private String password;
}
