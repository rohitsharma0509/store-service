package com.app.ecom.store.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecom.store.userservice.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByUserId(Long userId);
	
	void deleteByIdIn(List<Long> ids);
}