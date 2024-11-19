package com.crud.swagger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.swagger.Entity.User;
import com.crud.swagger.Repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public User createUser(User user) {
		try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating user: " + e.getMessage(), e);
        }
	}

	@Override
	public List<User> getAllUsers() {
		try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching users: " + e.getMessage(), e);
        }
	}

	@Override
	public Optional<User> getUserById(Long id) {
		try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching user by ID: " + e.getMessage(), e);
        }
	}

	@Override
	public User updateUser(Long id, User updatedUser) {
		try {
            return userRepository.findById(id).map(user -> {
                user.setFirstName(updatedUser.getFirstName());
                user.setLastName(updatedUser.getLastName());
                user.setEmail(updatedUser.getEmail());
                user.setUsername(updatedUser.getUsername());
                user.setProfilePictureUrl(updatedUser.getProfilePictureUrl());
                user.setPhoneNumber(updatedUser.getPhoneNumber());
                return userRepository.save(user);
            }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error while updating user: " + e.getMessage(), e);
        }
	}

	@Override
	public void deleteUser(Long id) {
		try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting user: " + e.getMessage(), e);
        }
		
	}
	
}
