package com.fiap.tech_challenge.service.domain;

import com.fiap.tech_challenge.controller.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDomain {

    private String userId;
    private String name;
    private String email;
    private String documentNumber;
    private String phone;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<AddressDomain> address;
    private UserType userType;


    public void createUserSave() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

}
