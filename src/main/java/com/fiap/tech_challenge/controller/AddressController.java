package com.fiap.tech_challenge.controller;

import com.fiap.tech_challenge.controller.dto.AddressRequestDto;
import com.fiap.tech_challenge.controller.dto.AddressResponseDto;
import com.fiap.tech_challenge.controller.dto.ErrorResponseDto;
import com.fiap.tech_challenge.mapper.AddressMapper;
import com.fiap.tech_challenge.service.create.CreateAddressService;
import com.fiap.tech_challenge.service.delete.DeleteAddressService;
import com.fiap.tech_challenge.service.read.ReadAddressService;
import com.fiap.tech_challenge.service.update.UpdateAddressService;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
@Tag(name = "Address Management", description = "Address creation, editing, deletion API")
public class AddressController {

    private final DeleteAddressService deleteAddressService;
    private final UpdateAddressService updateAddressService;
    private final CreateAddressService createAddressService;
    private final ReadAddressService readAddressService;
    private final AddressMapper addressMapper;

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

    @Operation(summary = "Create Address", description = "Create a new address in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAddress( @PathVariable String userId, @RequestBody @Valid AddressRequestDto request) {
        var addressDate = addressMapper.fromRequestToAddressDomain(request);
        createAddressService.createAddress(userId, addressDate);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Address by ID", description = "Update a address by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address update successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    public void updateAddressById(@PathVariable String id, @RequestBody @Valid AddressRequestDto request) {
        var userDomain = addressMapper.fromRequestToAddressDomain(request);
        updateAddressService.updateAddressById(id, userDomain);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Address by ID", description = "Get a address by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address update successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    public AddressResponseDto getAddressById(@PathVariable String id) {
        var address = readAddressService.readAddressById(id);
        return addressMapper.toAddressResponse(address);
    }

    @GetMapping("/{userId}/userId")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Address by user ID", description = "Get a address by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address update successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(schema = @Schema(implementation = ErrorResponseDto.class))})
    })
    public List<AddressResponseDto> getAddressByUserId(@PathVariable String userId) {
        var address = readAddressService.readAddressByUserId(userId);
        return addressMapper.toAddressResponseList(address);
    }
}
