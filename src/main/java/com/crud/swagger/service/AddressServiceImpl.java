package com.crud.swagger.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crud.swagger.dto.AddressDTO;
import com.crud.swagger.exceptions.AddressNotFoundException;
import com.crud.swagger.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.swagger.entity.Address;
import com.crud.swagger.entity.User;
import com.crud.swagger.Repo.AddressRepository;
import com.crud.swagger.Repo.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
    AddressMapper addressMapper;

	@Override
	public AddressDTO createAddress(AddressDTO addressDTO, Long userId) {
		Address createdAddress = addressMapper.toAddressEntity(addressDTO);
        addressRepository.save(createdAddress);
        return addressMapper.toAddressDTO(createdAddress);
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
	public AddressDTO updateAddress(Long id, AddressDTO updatedAddress) throws AddressNotFoundException {
		Address existingAddress = addressRepository.findById(id).
                orElseThrow(()->new AddressNotFoundException("address not found with ID:"+id));
        // Map updatedAddress data into the existing entity
        Address updatedAddressEntity = addressMapper.toAddressEntity(updatedAddress);

        // Ensure the ID remains the same
        updatedAddressEntity.setAddressId(updatedAddress.getAddressId());

        Address savedAddress = addressRepository.save(updatedAddressEntity);

        // Convert the saved entity back to DTO
        return addressMapper.toAddressDTO(savedAddress);

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
	
