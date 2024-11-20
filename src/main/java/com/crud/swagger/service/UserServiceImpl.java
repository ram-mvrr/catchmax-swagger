package com.crud.swagger.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crud.swagger.dto.UserDTO;
import com.crud.swagger.exceptions.UserNotFoundException;
import com.crud.swagger.mapper.AddressMapper;
import com.crud.swagger.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.swagger.entity.User;
import com.crud.swagger.Repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    /**
     * Create a new user from a UserDTO.
     *
     * @param userDTO The user data to create a new user
     * @return The created UserDTO
     */
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Convert UserDTO to User entity
        User user = userMapper.toUserEntity(userDTO);

        // Save the user entity in the repository
        User savedUser = userRepository.save(user);

        // Convert the saved User entity back to UserDTO
        return userMapper.toUserDTO(savedUser);
    }

    /**
     * Get all users and return them as a list of UserDTOs.
     *
     * @return List of UserDTOs
     */
    @Override
    public List<UserDTO> getAllUsers() {
        // Retrieve all User entities
        List<User> users = userRepository.findAll();

        // Convert each User entity to UserDTO
        return users.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a user by ID and return the corresponding UserDTO.
     *
     * @param id The user ID
     * @return Optional<UserDTO> containing the user DTO if found
     */
    @Override
    public Optional<UserDTO> getUserById(Long id) {
        // Find User by ID
        Optional<User> user = userRepository.findById(id);

        // Convert User entity to UserDTO if present
        return user.map(userMapper::toUserDTO);
    }

    /**
     * Update user details using the provided UserDTO.
     *
     * @param id             The ID of the user to update
     * @param updatedUserDTO The updated user data
     * @return Updated UserDTO
     */
    @Override
    public UserDTO updateUser(Long id, UserDTO updatedUserDTO) throws UserNotFoundException {
        // Find the existing user by ID
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        // Map updatedUserDTO to User entity
        User updatedUser = userMapper.toUserEntity(updatedUserDTO);

        // Retain existing user's ID to avoid overwriting it
        updatedUser.setUserId(existingUser.getUserId());

        // Save the updated user entity
        User savedUser = userRepository.save(updatedUser);

        // Convert the saved user entity back to UserDTO
        return userMapper.toUserDTO(savedUser);
    }

    /**
     * Delete a user by ID.
     *
     * @param id The user ID
     */
    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        // Check if the user exists by ID
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }

        // Delete the user entity
        userRepository.deleteById(id);
    }
}
