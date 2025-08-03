package com.fiap.tech_challenge.infrastructure.persistence.repository;

import com.fiap.tech_challenge.infrastructure.persistence.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, String> {

}
