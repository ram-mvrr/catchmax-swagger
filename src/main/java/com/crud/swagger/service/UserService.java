package com.crud.swagger.service;

import java.util.List;
import java.util.Optional;

import com.crud.swagger.dto.CreateUserDTO;
import com.crud.swagger.dto.UpdateUserDTO;
import com.crud.swagger.dto.UserDTO;
import com.crud.swagger.exceptions.EmailAlreadyExistsException;
import com.crud.swagger.exceptions.ResourceNotFoundException;
import com.crud.swagger.exceptions.UserNotFoundException;
import com.crud.swagger.exceptions.UsernameAlreadyExistsException;
import org.springframework.stereotype.Service;

import com.crud.swagger.entity.User;

@Service
public interface UserService {

	
	public UserDTO createUser(CreateUserDTO createUserDTO) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;

	public List<UserDTO> getAllUsers();

	public Optional<UserDTO> getUserById(Long id);

	public UserDTO updateUser(Long id, UpdateUserDTO updatedUserDTO) throws ResourceNotFoundException, UserNotFoundException;

	public void deleteUser(Long id) throws ResourceNotFoundException, UserNotFoundException;
}
