package com.app.ecom.store.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.PrivilegeDto;
import com.app.ecom.store.dto.SearchCriteria;
import com.app.ecom.store.mapper.PrivilegeMapper;
import com.app.ecom.store.model.Privilege;
import com.app.ecom.store.querybuilder.QueryBuilder;
import com.app.ecom.store.repository.PrivilegeRepository;
import com.app.ecom.store.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private PrivilegeMapper privilegeMapper;
	
	@Autowired
	private QueryBuilder queryBuilder;

	@Override
	public List<PrivilegeDto> getPrivileges() {
		return privilegeMapper.privilegesToPrivilegeDtos(privilegeRepository.findAll());
	}

	@Override
	public PrivilegeDto getPrivilegeById(Long id) {
		Optional<Privilege> optionalPrivilege = privilegeRepository.findById(id);
		return optionalPrivilege.isPresent() ? privilegeMapper.privilegeToPrivilegeDto(optionalPrivilege.get()) : null;
	}

	@Override
	public PrivilegeDto addPrivilege(PrivilegeDto privilegeDto) {
		return privilegeMapper.privilegeToPrivilegeDto(privilegeRepository.save(privilegeMapper.privilegeDtoToPrivilege(privilegeDto)));
	}

	@Override
	public CustomPage<PrivilegeDto> getPrivileges(Pageable pageable, Map<String, String> params) {
		List<SearchCriteria> criterias = new ArrayList<>();
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		
		StringBuilder query = new StringBuilder("select * from privileges where 1=1 ");
		StringBuilder countQuery = new StringBuilder("select count(privilege_id) count from privileges where 1=1 ");
		
		if(!StringUtils.isEmpty(params.get("name"))) {
			query.append(" and privilege_name like :name");
			countQuery.append(" and privilege_name like :name");
			criterias.add(new SearchCriteria("name", params.get("name"), Constants.LIKE));
		}
				
		query.append(" limit "+offset+", "+limit);
		System.out.println("Query: "+query);
		List<Privilege> privileges = queryBuilder.getByQuery(query.toString(), criterias, Privilege.class);
		Integer totalRecords = queryBuilder.countByQuery(countQuery.toString(), criterias);
		System.out.println(totalRecords);
		CustomPage<PrivilegeDto> page = new CustomPage<>();
		List<PrivilegeDto> privilegeDtos = privilegeMapper.privilegesToPrivilegeDtos(totalRecords > 0 ? new ArrayList<>(privileges) : null);
		page.setContent(CollectionUtils.isEmpty(privilegeDtos) ? null : new ArrayList<>(privilegeDtos));
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}

	@Override
	public List<PrivilegeDto> getPrivilegesByIdIn(List<Long> ids) {
		return privilegeMapper.privilegesToPrivilegeDtos(privilegeRepository.findByIdIn(ids));
	}

	@Override
	public boolean deletePrivilegeById(Long id) {
		boolean flag = false;
		try {
			privilegeRepository.deleteById(id);
			flag = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deletePrivileges(List<Long> ids) {
		boolean isDeleted = false;
		try {
			privilegeRepository.deleteByIdIn(ids);
			isDeleted = true;
		} catch(Exception e) {
			
		}
		return isDeleted;
	}

	@Override
	public List<PrivilegeDto> getAllPrivileges() {
		List<Privilege> privileges = privilegeRepository.findAll();
		return privilegeMapper.privilegesToPrivilegeDtos(privileges);
	}

	@Override
	public boolean deleteAllPrivileges() {
		boolean isDeleted = false;
		try {
			privilegeRepository.deleteAll();
			isDeleted = true;
		} catch(Exception e) {
			
		}
		return isDeleted;
	}
	
	@Override
	public boolean isRoleAssociatedWithPrivileges(List<Long> privilegeIds) {
		List<Privilege> privileges = privilegeRepository.findByIdIn(privilegeIds);
		return privileges.stream().anyMatch(p -> !CollectionUtils.isEmpty(p.getRoles()));
	}
}