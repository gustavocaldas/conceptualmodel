package com.gcaldas.conceptualmodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcaldas.conceptualmodel.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
