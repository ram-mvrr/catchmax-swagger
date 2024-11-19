package com.crud.swagger.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crud.swagger.Entity.Address;
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
    public ResponseEntity<?> createAddress(@RequestBody Address address, @PathVariable Long userId) {
        try {
            Address createdAddress = addressService.createAddress(address, userId);
            return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating address: {}", e.getMessage(), e);
            return new ResponseEntity<>("Error creating address: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all Addresses
    @GetMapping
    public ResponseEntity<?> getAllAddresses() {
        try {
            List<Address> addresses = addressService.getAllAddresses();
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching addresses: {}", e.getMessage(), e);
            return new ResponseEntity<>("Error fetching addresses: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Address by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Long id) {
        try {
            Optional<Address> address = addressService.getAddressById(id);
            if (address.isPresent()) {
                return new ResponseEntity<>(address.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Address not found with ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error fetching address by ID: {}", e.getMessage(), e);
            return new ResponseEntity<>("Error fetching address by ID: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Address by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody Address updatedAddress) {
        try {
            Address address = addressService.updateAddress(id, updatedAddress);
            if (address != null) {
                return new ResponseEntity<>(address, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Address not found with ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error updating address: {}", e.getMessage(), e);
            return new ResponseEntity<>("Error updating address: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Address by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        try {
            boolean isDeleted = addressService.deleteAddress(id);
            if (isDeleted) {
                return new ResponseEntity<>("Address deleted successfully", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Address not found with ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error deleting address: {}", e.getMessage(), e);
            return new ResponseEntity<>("Error deleting address: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
