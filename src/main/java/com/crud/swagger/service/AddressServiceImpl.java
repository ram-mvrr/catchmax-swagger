package com.crud.swagger.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crud.swagger.Repo.UserRepository;
import com.crud.swagger.dto.AddressDTO;
import com.crud.swagger.dto.CreateAddressDTO;
import com.crud.swagger.dto.UpdateAddressDTO;
import com.crud.swagger.entity.User;
import com.crud.swagger.exceptions.AddressNotFoundException;
import com.crud.swagger.exceptions.UserNotFoundException;
import com.crud.swagger.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.swagger.entity.Address;
import com.crud.swagger.Repo.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
    AddressMapper addressMapper;

	@Autowired
	UserRepository userRepository;

	@Override
	public CreateAddressDTO createAddress(CreateAddressDTO addressDTO, Long userId) throws UserNotFoundException {
		// Fetch the User entity by userId
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
		// Map the CreateAddressDTO to an Address entity
		Address createdAddress = addressMapper.toAddressEntity(addressDTO);

		// Associate the Address with the User
		createdAddress.setUser(user);

		// Save the Address entity
		addressRepository.save(createdAddress);

		// Convert the saved Address entity back to CreateAddressDTO
		return addressMapper.toCreateAddressDTO(createdAddress);
	}

	@Override
	public List<AddressDTO> getAllAddresses() {
		List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(addressMapper::toAddressDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<AddressDTO> getAddressById(Long id) {
		Address address = addressRepository.findById(id).orElse(null);
        return Optional.ofNullable(addressMapper.toAddressDTO(address));
	}

	@Override
	public UpdateAddressDTO updateAddress(Long id, UpdateAddressDTO updatedAddress) throws AddressNotFoundException {
		// Fetch the existing Address by ID
		Address existingAddress = addressRepository.findById(id)
				.orElseThrow(() -> new AddressNotFoundException("Address not found with ID: " + id));

		// Map updatedAddress data into the existing Address entity
		Address updatedAddressEntity = addressMapper.toAddressEntity(updatedAddress);

		// Retain the existing ID (since it's an update)
		updatedAddressEntity.setAddressId(existingAddress.getAddressId());

		// Ensure the Address remains associated with the same User (you can skip this if userId is not being changed)
		updatedAddressEntity.setUser(existingAddress.getUser());

		// Save the updated Address entity
		Address savedAddress = addressRepository.save(updatedAddressEntity);

		// Convert the saved Address entity back to UpdateAddressDTO
		return addressMapper.toUpdateAddressDTO(savedAddress);

	}

	@Override
	public void deleteAddress(Long id) throws AddressNotFoundException {
        if(addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        }else {
            throw new AddressNotFoundException("no address found with id "+id);
        }
    }

}
	
