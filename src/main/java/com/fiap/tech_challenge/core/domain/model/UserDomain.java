package com.fiap.tech_challenge.core.domain.model;

import com.fiap.tech_challenge.core.domain.model.type.UserType;
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
    private UserType userType;
    private List<AddressDomain> address;

    public void createUserSave() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateUser(UserDomain newDataUserDomain) {
        this.updatedAt = LocalDateTime.now();
        this.name = newDataUserDomain.getName();
        this.phone = newDataUserDomain.getPhone();
        this.email = newDataUserDomain.getEmail();
        this.userType= newDataUserDomain.getUserType();
        this.documentNumber = newDataUserDomain.getDocumentNumber();
    }

    public void createNewPassword(String newPassword) {
        this.updatedAt = LocalDateTime.now();
        this.password = newPassword;
    }

}
