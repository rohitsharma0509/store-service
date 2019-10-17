package com.app.ecom.store.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.PrivilegeDto;
import com.app.ecom.store.dto.RoleDto;
import com.app.ecom.store.dto.SearchCriteria;
import com.app.ecom.store.mapper.RoleMapper;
import com.app.ecom.store.model.Role;
import com.app.ecom.store.querybuilder.QueryBuilder;
import com.app.ecom.store.repository.RoleRepository;
import com.app.ecom.store.service.PrivilegeService;
import com.app.ecom.store.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@Autowired
	private QueryBuilder queryBuilder;
	
    @Override
	public CustomPage<RoleDto> getRoles(Pageable pageable, Map<String, String> params) {
		List<SearchCriteria> criterias = new ArrayList<>();
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		
		StringBuilder query = new StringBuilder("select * from role where 1=1 ");
		StringBuilder countQuery = new StringBuilder("select count(id) count from role where 1=1 ");
		
		if(!StringUtils.isEmpty(params.get("name"))) {
			//query.append(" and (first_name like :name or last_name like :name)");
			//countQuery.append(" and (first_name like :name or last_name like :name)");
			//criterias.add(new SearchCriteria("name", params.get("name"), Constants.LIKE));
		}
		if(!StringUtils.isEmpty(params.get("roleName"))){
			query.append(" and name like :roleName");
			countQuery.append(" and name like :roleName");
			criterias.add(new SearchCriteria("roleName", params.get("roleName"), Constants.LIKE));
		}
				
		query.append(" limit "+offset+", "+limit);
		System.out.println("Query: "+query);
		List<Role> roles = queryBuilder.getByQuery(query.toString(), criterias, Role.class);
		Integer totalRecords = queryBuilder.countByQuery(countQuery.toString(), criterias);
		System.out.println(totalRecords);
		CustomPage<RoleDto> page = new CustomPage<>();
		Set<RoleDto> roleDtos = roleMapper.rolesToRoleDtos(totalRecords > 0 ? new HashSet<>(roles) : null);
		page.setContent(CollectionUtils.isEmpty(roleDtos) ? null : new ArrayList<>(roleDtos));
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}

    @Transactional
	public RoleDto addRole(RoleDto roleDto) {
    	List<PrivilegeDto> privs = roleDto.getPrivilegeDtos().stream().filter(p -> Objects.nonNull(p) && null != p.getId() && p.getIsInRole()).collect(Collectors.toList());    	
    	roleDto.setPrivilegeDtos(new ArrayList<>());
    	for(PrivilegeDto priv: privs) {
    		roleDto.getPrivilegeDtos().add(privilegeService.getPrivilegeById(priv.getId()));
    	}
		return roleMapper.roleToRoleDto(roleRepository.save(roleMapper.roleDtoToRole(roleDto)));
	}
	
	@Override
	public RoleDto getRoleByName(String name) {
		return roleMapper.roleToRoleDto(roleRepository.findByName(name));
	}

	@Override
	public RoleDto getRoleById(Long id) {
		Optional<Role> optionalRole = roleRepository.findById(id);
		if(optionalRole.isPresent()) {
			return roleMapper.roleToRoleDto(optionalRole.get());
		} else {
			return null;
		}
	}
	
	@Override
	@Transactional
	public boolean deleteRoleById(Long id) {
		boolean flag = false;
		try {
			roleRepository.deleteById(id);
			flag = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	@Transactional
	public boolean deleteAllRoles() {
		boolean isDeleted = false;
		try {
			roleRepository.deleteAll();
			isDeleted = true;
		} catch(Exception e) {
			
		}
		return isDeleted;
	}

	@Override
	@Transactional
	public boolean deleteRoles(List<Long> ids) {
		boolean isDeleted = false;
		try {
			roleRepository.deleteByIdIn(ids);
			isDeleted = true;
		} catch(Exception e) {
			
		}
		return isDeleted;
	}

	@Override
	public Set<RoleDto> getRolesByIdIn(List<Long> ids) {
		return roleMapper.rolesToRoleDtos(roleRepository.findByIdIn(ids));
	}

	@Override
	public Set<RoleDto> getAllRoles() {
		List<Role> roles = roleRepository.findAll();
		return roleMapper.rolesToRoleDtos(new HashSet<>(roles));
	}
}
