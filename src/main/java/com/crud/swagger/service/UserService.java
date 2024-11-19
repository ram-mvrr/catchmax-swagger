package com.crud.swagger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crud.swagger.Entity.User;

@Service
public interface UserService {

	
	public User createUser(User user);
	
	public List<User> getAllUsers();
	
	public Optional<User> getUserById(Long id);
	
	public User updateUser(Long id, User updatedUser);
	
	public void deleteUser(Long id);
}
