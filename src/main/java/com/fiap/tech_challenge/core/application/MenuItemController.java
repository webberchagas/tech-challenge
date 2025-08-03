package com.fiap.tech_challenge.core.application;

import com.fiap.tech_challenge.core.dto.PagedResponseDto;
import com.fiap.tech_challenge.core.dto.address.ErrorResponseDto;
import com.fiap.tech_challenge.core.dto.menu.MenuItemRequestDto;
import com.fiap.tech_challenge.core.dto.menu.MenuItemResponseAllDto;
import com.fiap.tech_challenge.core.dto.menu.MenuItemResponseDto;
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

@Tag(name = "Menu Item Management", description = "API for managing menu items in restaurants")
public interface MenuItemController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create item for restaurant menu", description = "Creates a new item for a restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Menu item created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    MenuItemResponseDto createMenuItem(@RequestBody @Valid MenuItemRequestDto request);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve menu item by ID", description = "Retrieves menu item information using their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu item retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Menu item not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    MenuItemResponseDto getMenuItemById(@PathVariable String id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve all menu item for restaurant Id", description = "Retrieves a list of all menu item for restaurant Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu item retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No menu item found for restaurant Id",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    PagedResponseDto<MenuItemResponseAllDto> getAllMenuItemsByRestaurantId(@Parameter String id, @Parameter(description = "Page number") @RequestParam(defaultValue = "0") @Min(0) Integer page,
                                                                           @Parameter(description = "Page size") @RequestParam(defaultValue = "10") @Min(1) Integer size,
                                                                           @Parameter(description = "Sort criteria, example: name")
                                      @RequestParam(defaultValue = "name,asc") String sort
    );

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update menu item by ID", description = "Updates menu item information using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu item updated successfully"),
            @ApiResponse(responseCode = "404", description = "Menu item not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    MenuItemResponseDto updateMenuItemById(@PathVariable String id, @RequestBody @Valid MenuItemRequestDto request);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete menu item by ID", description = "Deletes a menu item using their unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Menu item deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Menu item not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    void deleteMenuItemById(@PathVariable String id);

}
