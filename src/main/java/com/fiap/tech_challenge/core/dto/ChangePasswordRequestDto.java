package com.fiap.tech_challenge.core.dto;


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
@Schema(description = "Request body for changing a user's password")
public class ChangePasswordRequestDto {

    @NotEmpty
    @Email
    @Schema(description = "User email, used for identification and login", example = "johndoe@email.com")
    private String email;

    @NotEmpty
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    @Schema(description = "New password", example = "B7891011#")
    private String newPassword;

    @NotEmpty
    @Schema(description = "Password confirmation", example = "B7891011#")
    private String confirmNewPassword;
}
