package com.crud.swagger.mapper;

import com.crud.swagger.dto.AddressDTO;
import com.crud.swagger.dto.CreateAddressDTO;
import com.crud.swagger.dto.UpdateAddressDTO;
import com.crud.swagger.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    // Mapping from Address entity to CreateAddressDTO
    @Mapping(source = "user.userId", target = "userId")    // Map userId from Address to DTO
    CreateAddressDTO toCreateAddressDTO(Address address);

    // Mapping from Address entity to UpdateAddressDTO
    @Mapping(source = "addressId", target = "addressId")  // Ensure addressId is mapped
    @Mapping(source = "user.userId", target = "userId")    // Map userId from Address to DTO
    UpdateAddressDTO toUpdateAddressDTO(Address address);

    // Mapping from AddressDTO to Address entity (Update)
    @Mapping(source = "addressId", target = "addressId")  // Ensure addressId is mapped back
    @Mapping(source = "userId", target = "user.userId")    // Map userId from DTO to Address entity
    Address toAddressEntity(UpdateAddressDTO updateAddressDTO);

    // Mapping from AddressDTO to Address entity (Create)
    @Mapping(source = "userId", target = "user.userId")    // Map userId from DTO to Address entity
    @Mapping(target = "addressId", ignore = true) // Ignore addressId in the mapping
    Address toAddressEntity(CreateAddressDTO createAddressDTO);

    // Mapping from Address entity to AddressDTO
    @Mapping(source = "addressId", target = "addressId")  // Ensure addressId is mapped
    @Mapping(source = "user.userId", target = "userId")    // Map userId from Address to DTO
    AddressDTO toAddressDTO(Address address);

    // Mapping from AddressDTO to Address entity (when retrieving AddressDTO)
    @Mapping(source = "addressId", target = "addressId")  // Ensure addressId is mapped back
    @Mapping(source = "userId", target = "user.userId")    // Map userId from DTO to Address entity
    Address toAddressEntity(AddressDTO addressDTO);
}
