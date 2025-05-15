package com.fiap.tech_challenge.repository.mapper;

import com.fiap.tech_challenge.controller.dto.UserResponseDto;
import com.fiap.tech_challenge.model.UserEntity;
import com.fiap.tech_challenge.service.domain.UserDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperEntity {

    UserEntity toEntity(UserDomain userDomain);
    UserResponseDto toResponseDto(UserEntity userEntity);
}
