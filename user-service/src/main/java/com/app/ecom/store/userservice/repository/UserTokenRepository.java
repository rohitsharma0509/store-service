package com.app.ecom.store.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ecom.store.userservice.model.UserToken;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
	
}