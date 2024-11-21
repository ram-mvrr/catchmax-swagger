package com.crud.swagger.service;

import java.util.List;
import java.util.Optional;

import com.crud.swagger.dto.AddressDTO;
import com.crud.swagger.dto.CreateAddressDTO;
import com.crud.swagger.dto.UpdateAddressDTO;
import com.crud.swagger.exceptions.AddressNotFoundException;
import com.crud.swagger.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {


	public CreateAddressDTO createAddress(CreateAddressDTO addressDTO, Long userId) throws UserNotFoundException;
	public List<AddressDTO> getAllAddresses();
	public Optional<AddressDTO> getAddressById(Long id);
	public UpdateAddressDTO updateAddress(Long id, UpdateAddressDTO updatedAddress) throws AddressNotFoundException;
	public void deleteAddress(Long id) throws AddressNotFoundException;
}
