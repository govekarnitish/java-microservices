package com.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
