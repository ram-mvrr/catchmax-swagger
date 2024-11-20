package com.crud.swagger.controller;

import com.crud.swagger.dto.UserDTO;
import com.crud.swagger.exceptions.ResourceNotFoundException;
import com.crud.swagger.exceptions.UserNotFoundException;
import com.crud.swagger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    /**
     * Create a new user.
     *
     * @param userDTO User data
     * @return Created UserDTO
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Get all users.
     *
     * @return List of UserDTOs
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Get a user by ID.
     *
     * @param id User ID
     * @return UserDTO if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update a user by ID.
     *
     * @param id             User ID
     * @param updatedUserDTO Updated User data
     * @return Updated UserDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) throws UserNotFoundException {
        UserDTO updatedUser = userService.updateUser(id, updatedUserDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    /**
     * Delete a user by ID.
     *
     * @param id User ID
     * @return Confirmation message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
