package com.fiap.tech_challenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @Size(min = 8, max = 15, message = "Password must be at least 8 characters and maximum 15 characters")
    @Schema(description = "User password", example = "A123456*")
    private String password;
}
