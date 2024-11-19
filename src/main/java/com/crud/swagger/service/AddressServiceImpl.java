package com.crud.swagger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.swagger.Entity.Address;
import com.crud.swagger.Entity.User;
import com.crud.swagger.Repo.AddressRepository;
import com.crud.swagger.Repo.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Address createAddress(Address address, Long userId) {
		try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
            address.setUser(user);
            return addressRepository.save(address);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating address: " + e.getMessage(), e);
        }
	}

	@Override
	public List<Address> getAllAddresses() {
		try {
            return addressRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching addresses: " + e.getMessage(), e);
        }
	}

	@Override
	public Optional<Address> getAddressById(Long id) {
		try {
            return addressRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching address by ID: " + e.getMessage(), e);
        }
	}

	@Override
	public Address updateAddress(Long id, Address updatedAddress) {
		try {
            return addressRepository.findById(id).map(address -> {
                address.setFirstName(updatedAddress.getFirstName());
                address.setLastName(updatedAddress.getLastName());
                address.setCompany(updatedAddress.getCompany());
                address.setAddress(updatedAddress.getAddress());
                address.setApartment(updatedAddress.getApartment());
                address.setCity(updatedAddress.getCity());
                address.setCountry(updatedAddress.getCountry());
                address.setStateProvince(updatedAddress.getStateProvince());
                address.setPostalCode(updatedAddress.getPostalCode());
                return addressRepository.save(address);
            }).orElseThrow(() -> new RuntimeException("Address not found with ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error while updating address: " + e.getMessage(), e);
        }
	}

	@Override
	public boolean deleteAddress(Long id) {
		try {
            addressRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting address: " + e.getMessage(), e);
        }
		return false;
    }
		
}
	
