package com.fiap.tech_challenge.controller;

import com.fiap.tech_challenge.controller.dto.ChangePasswordRequestDto;
import com.fiap.tech_challenge.controller.dto.ErrorResponseDto;
import com.fiap.tech_challenge.controller.dto.LoginRequestDto;
import com.fiap.tech_challenge.mapper.LoginMapper;
import com.fiap.tech_challenge.service.login.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/logins")
@RequiredArgsConstructor
@Tag(name = "Login Management", description = "Login validate and password change API")
public class LoginController {

    private final LoginMapper loginMapper;
    private final LoginService loginService;

    @Operation(summary = "Validate Login", description = "Validate e-mail and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Validate login successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean validateLogin(@RequestBody @Valid LoginRequestDto request) {
        return loginService.validateLogin(loginMapper.toDomainLogin(request));
    }

    @Operation(summary = "Change password", description = "Create a new password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created new password successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    @PostMapping("/changePassword")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPassword(@RequestBody @Valid ChangePasswordRequestDto request) {
        loginService.createPassword(loginMapper.toDomainPassword(request));
    }
}
