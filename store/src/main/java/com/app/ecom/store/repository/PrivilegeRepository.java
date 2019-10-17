package com.app.ecom.store.repository;

import java.util.List;

import com.app.ecom.store.dto.PrivilegeDto;
import com.app.ecom.store.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	List<PrivilegeDto> findByParentIdIsNull();
	
	void deleteByIdIn(List<Long> ids);
	
	List<Privilege> findByIdIn(List<Long> ids);

}
