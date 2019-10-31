package com.app.ecom.store.userservice.repository;

import java.util.List;

import com.app.ecom.store.userservice.dto.PrivilegeDto;
import com.app.ecom.store.userservice.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	List<PrivilegeDto> findByParentIdIsNull();
	
	void deleteByIdIn(List<Long> ids);
	
	List<Privilege> findByIdIn(List<Long> ids);

}