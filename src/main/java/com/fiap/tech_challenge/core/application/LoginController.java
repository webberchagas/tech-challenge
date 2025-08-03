package com.fiap.tech_challenge.core.application;


import com.fiap.tech_challenge.core.dto.login.ChangePasswordRequestDto;
import com.fiap.tech_challenge.core.dto.address.ErrorResponseDto;
import com.fiap.tech_challenge.core.dto.login.LoginRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Login Management", description = "API for user login validation and password management")
public interface LoginController {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Validate user credentials", description = "Validates if the provided email and password are correct")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login validated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    Boolean validateLogin(@RequestBody @Valid LoginRequestDto request);

    @PostMapping("/changePassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Change user password", description = "Changes the user's password with a new one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Password changed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    void createPassword(@RequestBody @Valid ChangePasswordRequestDto request);

}
