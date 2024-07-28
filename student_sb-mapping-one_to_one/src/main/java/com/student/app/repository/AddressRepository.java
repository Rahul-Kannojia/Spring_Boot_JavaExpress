package com.student.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.app.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	
	Optional<Address> findByMobileNumber(String mobileNumber);
}
