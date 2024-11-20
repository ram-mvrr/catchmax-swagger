package com.crud.swagger.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.swagger.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
}
