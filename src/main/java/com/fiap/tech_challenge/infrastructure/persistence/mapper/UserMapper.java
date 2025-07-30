package com.fiap.tech_challenge.infrastructure.persistence.mapper;

import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.dto.user.UserCreationRequestDto;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserUpdateRequestDto;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDomain toDomain(UserCreationRequestDto userDto);
    UserDomain toDomainUpdate(UserUpdateRequestDto userDto);
    UserEntity toEntity(UserDomain userDomain);
    UserResponseDto toResponseDto(UserEntity userEntity);
//    UserResponseDto fromDomainToResponseDto(UserDomain userDomain);

    @Mapping(target = "address", ignore = true)
    UserDomain fromEntityToDomain(UserEntity userEntity);
}
