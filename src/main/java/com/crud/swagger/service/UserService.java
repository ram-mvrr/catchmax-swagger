package com.crud.swagger.service;

import java.util.List;
import java.util.Optional;

import com.crud.swagger.dto.UserDTO;
import com.crud.swagger.exceptions.ResourceNotFoundException;
import com.crud.swagger.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import com.crud.swagger.entity.User;

@Service
public interface UserService {

	
	public UserDTO createUser(UserDTO userDTO);

	public List<UserDTO> getAllUsers();

	public Optional<UserDTO> getUserById(Long id);

	public UserDTO updateUser(Long id, UserDTO updatedUserDTO) throws ResourceNotFoundException, UserNotFoundException;

	public void deleteUser(Long id) throws ResourceNotFoundException, UserNotFoundException;
}
