package com.app.ecom.store.userservice.repository;

import java.util.List;

import com.app.ecom.store.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByIdIn(List<Long> ids);

	void deleteByIdIn(List<Long> ids);

	Role findByName(String name);
}
