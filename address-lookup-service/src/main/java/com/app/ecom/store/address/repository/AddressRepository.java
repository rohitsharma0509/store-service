package com.app.ecom.store.address.repository;

import java.util.List;

import com.app.ecom.store.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByUserId(Long userId);
	
	void deleteByIdIn(List<Long> ids);
}