package com.tiagoamp.userinfo.service;

import com.tiagoamp.userinfo.controller.UserRequestDTO;
import com.tiagoamp.userinfo.controller.UserResponseDTO;
import com.tiagoamp.userinfo.domain.User;
import com.tiagoamp.userinfo.repository.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel="spring")
public interface UserMapper {

    User toModel(UserRequestDTO dto);

    User toModel(UserEntity entity);

    List<User> toModel(List<UserEntity> entities);

    UserEntity toEntity(User model);

    UserResponseDTO toResponseDTO(User model);

    List<UserResponseDTO> toResponseDTO(List<User> models);

}
