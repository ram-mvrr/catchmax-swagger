package com.crud.swagger.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.swagger.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
