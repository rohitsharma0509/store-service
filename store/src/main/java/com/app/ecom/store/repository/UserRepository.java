package com.app.ecom.store.repository;

import java.util.Set;

import com.app.ecom.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	Set<User> findByMobileContaining(String mobileOrName);

	User findByEmail(String email);
}
