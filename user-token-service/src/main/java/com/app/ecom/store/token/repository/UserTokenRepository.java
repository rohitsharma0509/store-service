package com.app.ecom.store.token.repository;

import java.util.List;

import com.app.ecom.store.token.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
	
}