package com.app.ecom.store.repository;

import java.util.List;
import java.util.Set;

import com.app.ecom.store.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Set<Role> findByIdIn(List<Long> ids);

	void deleteByIdIn(List<Long> ids);

	Role findByName(String name);
}
