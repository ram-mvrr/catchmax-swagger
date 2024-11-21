package com.crud.swagger.Repo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.swagger.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(@NotNull @Email String email);

    boolean existsByUsername(@NotNull String username);
}
