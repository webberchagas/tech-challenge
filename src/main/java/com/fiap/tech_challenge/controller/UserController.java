package com.fiap.tech_challenge.controller;

import com.fiap.tech_challenge.controller.dto.ErrorResponseDto;
import com.fiap.tech_challenge.controller.dto.UserRequestDto;
import com.fiap.tech_challenge.controller.dto.UserResponseDto;
import com.fiap.tech_challenge.controller.mapper.UserMapper;
import com.fiap.tech_challenge.controller.type.UserType;
import com.fiap.tech_challenge.service.ConsultUserService;
import com.fiap.tech_challenge.service.CreateUserService;
import com.fiap.tech_challenge.service.DeleteUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "User creation, editing, deletion and search API")
public class UserController {

    private final CreateUserService createUserService;
    private final ConsultUserService consultUserService;
    private final DeleteUserService deleteUserService;
    private final UserMapper userMapper;

    @Operation(summary = "Create User", description = "Create a new user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserRequestDto request) {
        var userDate = userMapper.toDomain(request);
        createUserService.createUser(userDate);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get User by ID", description = "Retrieve user information by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    public UserResponseDto getUserById(@PathVariable String id) {
        return consultUserService.getUserById(id);
    }

    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get User by e-mail", description = "Retrieve user information by user e-mail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    public UserResponseDto getUserByEmail(@RequestParam("e-mail") String email) {
        return consultUserService.getUserByEmail(email);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all users for user type", description = "Retrieve all users for user type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    public List<UserResponseDto> getUserByUserType(@RequestParam("user_type") @Parameter(name = "user_type", description = "User type") final UserType userType) {
        return consultUserService.getUserByUserType(userType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete User by ID", description = "Delete a user by their unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    public void deleteUserById(@PathVariable String id) {
        deleteUserService.deleteUserById(id);
    }

}
