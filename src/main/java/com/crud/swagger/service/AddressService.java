package com.crud.swagger.service;

import java.util.List;
import java.util.Optional;

import com.crud.swagger.dto.AddressDTO;
import com.crud.swagger.exceptions.AddressNotFoundException;
import org.springframework.stereotype.Service;

import com.crud.swagger.entity.Address;

@Service
public interface AddressService {


	public AddressDTO createAddress(AddressDTO addressDTO, Long userId);
	public List<AddressDTO> getAllAddresses();
	public Optional<AddressDTO> getAddressById(Long id);
	public AddressDTO updateAddress(Long id, AddressDTO updatedAddress) throws AddressNotFoundException;
	public void deleteAddress(Long id) throws AddressNotFoundException;
}
