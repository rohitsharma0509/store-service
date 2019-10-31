package com.app.ecom.store.userservice.repository;

import java.util.List;
import java.util.Set;

import com.app.ecom.store.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	void deleteByIdIn(List<Long> ids);
	
	User findByUsername(String username);

	Set<User> findByMobileContaining(String mobileOrName);

	User findByEmail(String email);
}
