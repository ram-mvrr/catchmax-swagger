package com.crud.swagger.mapper;


import com.crud.swagger.entity.Address;
import com.crud.swagger.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(source = "user.userId", target = "userId")  // Map userId from the Address's User entity to AddressDTO
    AddressDTO toAddressDTO(Address address);

    @Mapping(source = "userId", target = "user.userId")  // Assuming you have a method to get the user by userId
    Address toAddressEntity(AddressDTO addressDTO);
}

