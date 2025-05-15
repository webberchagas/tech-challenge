package com.fiap.tech_challenge.controller;

import com.fiap.tech_challenge.controller.dto.ErrorResponseDto;
import com.fiap.tech_challenge.service.DeleteAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
@Tag(name = "Address Management", description = "Address creation, editing, deletion API")
public class AddressController {

    private final DeleteAddressService deleteAddressService;

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Address by ID", description = "Delete a address by their unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    public void deleteAddressById(@PathVariable String id) {
        deleteAddressService.deleteAddressById(id);
    }
}
