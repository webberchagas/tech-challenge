package com.fiap.tech_challenge.infrastructure.persistence.repository;


import com.fiap.tech_challenge.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByDocumentNumber(String userId);
    Optional<UserEntity> findById(String id);

}
