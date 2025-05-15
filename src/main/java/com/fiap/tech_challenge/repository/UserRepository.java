package com.fiap.tech_challenge.repository;

import com.fiap.tech_challenge.controller.type.UserType;
import com.fiap.tech_challenge.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByDocumentNumber(String userId);
    List<UserEntity> findByUserType(UserType userType);

}
