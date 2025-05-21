package com.fiap.tech_challenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.tech_challenge.controller.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private String userId;
    private String name;
    private String documentNumber;
    private String email;
    private String phone;
    private String password;
    private UserType userType;
    private List<AddressResponseDto> address;

}
