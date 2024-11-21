package com.crud.swagger.dto;

import lombok.Data;

@Data
public class CreateAddressDTO {

    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String apartment;
    private String city;
    private String country;
    private String stateProvince;
    private String postalCode;
    private Long userId;
}

