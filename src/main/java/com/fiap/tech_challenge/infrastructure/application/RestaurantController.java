package com.fiap.tech_challenge.infrastructure.application;

import com.fiap.tech_challenge.core.dto.address.ErrorResponseDto;
import com.fiap.tech_challenge.core.dto.restaurant.RestaurantRequestDto;
import com.fiap.tech_challenge.core.dto.restaurant.RestaurantResponseDto;
import com.fiap.tech_challenge.core.dto.PagedResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Restaurant Management", description = "API for managing restaurants")
public interface RestaurantController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create restaurant", description = "Creates a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    RestaurantResponseDto createRestaurant(@RequestBody @Valid RestaurantRequestDto request);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve restaurant by ID", description = "Retrieves restaurant information using their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    RestaurantResponseDto getRestaurantById(@PathVariable String id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve all restaurant", description = "Retrieves a list of all restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No restaurant found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    PagedResponseDto<RestaurantResponseDto> getAllRestaurants(@Parameter(description = "Page number") @RequestParam(defaultValue = "0") @Min(0) Integer page,
                                                              @Parameter(description = "Page size") @RequestParam(defaultValue = "10") @Min(1) Integer size,
                                                              @Parameter(description = "Sort criteria, example: name")
                                      @RequestParam(defaultValue = "restaurantName,asc") String sort
    );

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update restaurant by ID", description = "Updates restaurant information using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant updated successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    RestaurantResponseDto updateRestaurantById(@PathVariable String id, @RequestBody @Valid RestaurantRequestDto request);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete restaurant by ID", description = "Deletes a restaurant using their unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Restaurant deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    void deleteRestaurantById(@PathVariable String id);

}
