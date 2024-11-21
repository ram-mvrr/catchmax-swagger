package com.crud.swagger.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crud.swagger.dto.CreateUserDTO;
import com.crud.swagger.dto.UpdateUserDTO;
import com.crud.swagger.dto.UserDTO;
import com.crud.swagger.exceptions.EmailAlreadyExistsException;
import com.crud.swagger.exceptions.UserNotFoundException;
import com.crud.swagger.exceptions.UsernameAlreadyExistsException;
import com.crud.swagger.mapper.AddressMapper;
import com.crud.swagger.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.swagger.entity.User;
import com.crud.swagger.Repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    /**
     * Create a new user from a UserDTO.
     *
     * @param createUserDTO The user data to create a new user
     * @return The created UserDTO
     */
    @Override
    public UserDTO createUser(CreateUserDTO createUserDTO) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        // Validate incoming data if needed
        if (userRepository.existsByEmail(createUserDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already in use");
        }
        if (userRepository.existsByUsername(createUserDTO.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already in use");
        }
        // Convert UserDTO to User entity
        User user = userMapper.toUserEntity(createUserDTO);

        // If full name is not provided, create it from firstName and lastName
        if (user.getFullName() == null) {
            user.setFullName(user.getFirstName() + " " + user.getLastName());
        }

        // Set additional fields like userSince
        user.setUserSince(LocalDateTime.now());
        user.setLoginCount(0);
        user.setUserPoint(0);


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
    public UserDTO updateUser(Long id, UpdateUserDTO updatedUserDTO) throws UserNotFoundException {
        // Find the existing user by ID
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        // Map the updated user DTO to the existing user entity
        User updatedUser = userMapper.toUserEntity(updatedUserDTO);

        // Retain existing values for any fields that are not provided in the UpdateUserDTO
        if (updatedUserDTO.getFirstName() == null) {
            updatedUser.setFirstName(existingUser.getFirstName());
        }
        if (updatedUserDTO.getLastName() == null) {
            updatedUser.setLastName(existingUser.getLastName());
        }
        if (updatedUserDTO.getFullName() == null) {
            updatedUser.setFullName(existingUser.getFullName());
        }
        if (updatedUserDTO.getEmail() == null) {
            updatedUser.setEmail(existingUser.getEmail());
        }
        if (updatedUserDTO.getUsername() == null) {
            updatedUser.setUsername(existingUser.getUsername());
        }
        if (updatedUserDTO.getProfilePictureUrl() == null) {
            updatedUser.setProfilePictureUrl(existingUser.getProfilePictureUrl());
        }
        if (updatedUserDTO.getPhoneNumber() == null) {
            updatedUser.setPhoneNumber(existingUser.getPhoneNumber());
        }
        if (updatedUserDTO.getAccountStatus() == null) {
            updatedUser.setAccountStatus(existingUser.getAccountStatus());
        }
        if (updatedUserDTO.getNotificationsEnabled() == null) {
            updatedUser.setNotificationsEnabled(existingUser.getNotificationsEnabled());
        }
        if (updatedUserDTO.getGender() == null) {
            updatedUser.setGender(existingUser.getGender());
        }
        if (updatedUserDTO.getDateOfBirth() == null) {
            updatedUser.setDateOfBirth(existingUser.getDateOfBirth());
        }
        if (updatedUserDTO.getLocation() == null) {
            updatedUser.setLocation(existingUser.getLocation());
        }

        // These fields are not part of the UpdateUserDTO, so we retain existing values.
        updatedUser.setLastActivity(existingUser.getLastActivity());
        updatedUser.setLoginCount(existingUser.getLoginCount());
        updatedUser.setUserPoint(existingUser.getUserPoint());
        updatedUser.setLastLoginDate(existingUser.getLastLoginDate());
        updatedUser.setPassword(existingUser.getPassword());
        updatedUser.setUserSince(existingUser.getUserSince());

        // Save the updated user entity
        User savedUser = userRepository.save(updatedUser);

        // Convert the saved User entity back to UserDTO
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
