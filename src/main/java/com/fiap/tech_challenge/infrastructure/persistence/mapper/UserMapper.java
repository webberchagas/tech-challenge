package com.fiap.tech_challenge.infrastructure.persistence.mapper;

import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.dto.user.UserCreationRequestDto;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import com.fiap.tech_challenge.core.dto.user.UserUpdateRequestDto;
import com.fiap.tech_challenge.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface UserMapper {

    UserDomain toDomain(UserCreationRequestDto userDto);
    UserDomain toDomainUpdate(UserUpdateRequestDto userDto);
    UserEntity toEntity(UserDomain userDomain);
    UserResponseDto toResponseDto(UserDomain userDomain);

    @Mapping(target = "address.user", ignore = true)
    UserDomain toDomain(UserEntity userEntity);

    @Named("userWithoutAddress")
    @Mapping(target = "address", ignore = true)
    UserResponseDto toResponseDtoWithoutAddress(UserDomain userDomain);
}
