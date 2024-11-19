package com.crud.swagger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crud.swagger.Entity.Address;

@Service
public interface AddressService {

	
	public Address createAddress(Address address, Long userId);
	public List<Address> getAllAddresses();
	public Optional<Address> getAddressById(Long id);
	public Address updateAddress(Long id, Address updatedAddress);
	public boolean deleteAddress(Long id);
}
