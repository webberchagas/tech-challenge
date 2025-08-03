package com.fiap.tech_challenge.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurants")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "restaurant_id")
    private String restaurantId;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private String cnpj;

    @Column(name = "cuisine_type", nullable = false)
    private String cuisineType;

    @Column(name = "opening_hours", nullable = false)
    private String openingHours;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private RestaurantAddressEntity address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItemEntity> menuItems;
}
