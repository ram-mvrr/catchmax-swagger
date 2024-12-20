package com.crud.swagger.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class UserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String username;
    private String profilePictureUrl;
    private String phoneNumber;
    private String accountStatus;
    private Boolean notificationsEnabled;
    private String gender;
    private LocalDateTime dateOfBirth;
    private String location;
    private LocalDateTime lastActivity;
    private Integer loginCount;
    private LocalDateTime userSince;
    private Integer userPoint;
    private List<AddressDTO> addresses; // Include AddressDTO for one-to-many relationship
}


