package com.fiap.tech_challenge.infrastructure.persistence.repository;

import com.fiap.tech_challenge.infrastructure.persistence.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, String> {
    Optional<RestaurantEntity> findByCnpj(String cnpj);
    List<RestaurantEntity> findByUser_UserId(String userId);
}
