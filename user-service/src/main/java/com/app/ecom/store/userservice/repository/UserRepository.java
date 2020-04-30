package com.app.ecom.store.userservice.repository;

import java.util.List;
import java.util.Set;

import com.app.ecom.store.userservice.constants.Constants;
import com.app.ecom.store.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	void deleteByIdIn(List<Long> ids);
	
	User findByUsername(String username);

	Set<User> findByMobileContaining(String mobileOrName);

	User findByEmail(String email);

	@Modifying
	@Query(value = "update users set password=:pswrd where user_id=:userId", nativeQuery = true)
	void updatePassword(@Param(Constants.USER_ID) Long userId, @Param(Constants.PSWRD) String pswrd);
}
