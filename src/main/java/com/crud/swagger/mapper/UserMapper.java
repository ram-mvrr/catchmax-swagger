package com.crud.swagger.mapper;

import com.crud.swagger.dto.CreateUserDTO;
import com.crud.swagger.dto.UpdateUserDTO;
import com.crud.swagger.entity.User;
import com.crud.swagger.dto.UserDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface UserMapper {

    // Mapping from User entity to UserDTO
    @Mapping(source = "userId", target = "userId")  // If you want to map userId explicitly
    @Mapping(source = "addresses", target = "addresses") // Map addresses collection to AddressDTOs
    UserDTO toUserDTO(User user);

    // Mapping from CreateUserDTO to User entity
    @Mapping(target = "userId", ignore = true) // Ignore userId during creation
    @Mapping(target = "addresses", ignore = true) // If addresses are not provided in CreateUserDTO
    User toUserEntity(CreateUserDTO createUserDTO);

    User toUserEntity(UpdateUserDTO updateUserDTO);

}

