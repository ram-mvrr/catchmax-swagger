package com.crud.swagger.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreateUserDTO {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String fullName; // Optional; can be generated as `firstName + lastName` in the service

    @NotNull
    @Email
    private String email;

    @NotNull
    private String username;

    private String profilePictureUrl;
    private String phoneNumber;

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password; // Accept plaintext password, which will be hashed in the service

    private String accountStatus = "active"; // Default value

    private Boolean notificationsEnabled = true; // Default value

    private String gender;
    private LocalDateTime dateOfBirth;
    private String location;
}
