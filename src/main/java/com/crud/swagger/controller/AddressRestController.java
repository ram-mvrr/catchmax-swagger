package com.crud.swagger.controller;

import java.util.List;
import java.util.Optional;

import com.crud.swagger.dto.AddressDTO;
import com.crud.swagger.dto.CreateAddressDTO;
import com.crud.swagger.dto.UpdateAddressDTO;
import com.crud.swagger.exceptions.AddressNotFoundException;
import com.crud.swagger.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crud.swagger.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/addresses")
public class AddressRestController {

    private static final Logger logger = LoggerFactory.getLogger(AddressRestController.class);

    @Autowired
    private AddressService addressService;

    // Create Address
    @PostMapping("/{userId}")
    public ResponseEntity<CreateAddressDTO> createAddress(@RequestBody CreateAddressDTO addressDTO, @PathVariable Long userId) throws UserNotFoundException {
        CreateAddressDTO createdAddress =  addressService.createAddress(addressDTO, userId);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    // Get all Addresses
    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<AddressDTO> addressDTOS = addressService.getAllAddresses();
        return new ResponseEntity<>(addressDTOS, HttpStatus.OK);
    }

    // Get Address by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Optional<AddressDTO> addressDTO = addressService.getAddressById(id);
        return new ResponseEntity<>(addressDTO.orElse(null), HttpStatus.OK);
    }
    // Update Address by ID
    @PutMapping("/{id}")
    public ResponseEntity<UpdateAddressDTO> updateAddress(@PathVariable Long id, @RequestBody UpdateAddressDTO updatedAddress) throws AddressNotFoundException {
       UpdateAddressDTO addressDTO = addressService.updateAddress(id,updatedAddress);
       return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }

    // Delete Address by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) throws AddressNotFoundException {
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Address Deleted", HttpStatus.OK);
    }
}
