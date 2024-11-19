package com.crud.swagger.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.swagger.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
