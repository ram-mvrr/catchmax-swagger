package com.crud.swagger.mapper;

import com.crud.swagger.entity.User;
import com.crud.swagger.dto.UserDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    User toUserEntity(UserDTO userDTO);
}

