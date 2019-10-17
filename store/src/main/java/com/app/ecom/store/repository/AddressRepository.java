package com.app.ecom.store.repository;

import java.util.Set;

import com.app.ecom.store.model.Address;
import com.app.ecom.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	Set<Address> findByUser(User user);
}
