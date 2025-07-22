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
        this.password = newPassword;
    }


//    private void validateName(String name){
//        if(name == null || name.isEmpty()){
//            throw new IllegalArgumentException("Name cannot be null or empty");
//        }
//    }
//
//    private void validateEmail(String email){
//        if(email == null || email.isEmpty()){
//            throw new IllegalArgumentException("Email cannot be null or empty");
//        }
//    }
//
//    private void validateDocumentNumber(String documentNumber){
//        if(documentNumber == null || documentNumber.isEmpty()){
//            throw new IllegalArgumentException("Document number cannot be null or empty");
//        }
//    }
//
//    private void validatePhone(String phone){
//        if(phone == null || phone.isEmpty()){
//            throw new IllegalArgumentException("Phone number cannot be null or empty");
//        }
//    }
//
//    private void validatePassword(String password){
//        if(password == null || password.isEmpty()){
//            throw new IllegalArgumentException("Password cannot be null or empty");
//        }
//    }
//
//    private void validateUserType(UserType userType){
//        if(userType == null || userType == null){
//            throw new IllegalArgumentException("UserType cannot be null or empty");
//        }
//    }
}
