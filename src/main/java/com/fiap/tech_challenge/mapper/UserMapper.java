package com.fiap.tech_challenge.mapper;

import com.fiap.tech_challenge.controller.dto.UserCreationRequestDto;
import com.fiap.tech_challenge.controller.dto.UserResponseDto;
import com.fiap.tech_challenge.controller.dto.UserUpdateRequestDto;
import com.fiap.tech_challenge.entity.UserEntity;
import com.fiap.tech_challenge.domain.UserDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDomain toDomain(UserCreationRequestDto userDto);
    UserDomain toDomainUpdate(UserUpdateRequestDto userDto);
    UserEntity toEntity(UserDomain userDomain);
    UserResponseDto toResponseDto(UserEntity userEntity);
    UserResponseDto fromDomainToResponseDto(UserDomain userDomain);

    @Mapping(target = "address", ignore = true)
    UserDomain fromEntityToDomain(UserEntity userEntity);
}
