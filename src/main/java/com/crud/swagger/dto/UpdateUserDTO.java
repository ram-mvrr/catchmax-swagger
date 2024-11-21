package com.crud.swagger.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateUserDTO {
    @NotNull
    private Long userId; // Required for identifying the user

    private String firstName;
    private String lastName;
    private String fullName;

    @Email
    private String email;

    private String username;
    private String profilePictureUrl;
    private String phoneNumber;
    private String accountStatus;
    private Boolean notificationsEnabled;
    private String gender;
    private LocalDateTime dateOfBirth;
    private String location;
}
