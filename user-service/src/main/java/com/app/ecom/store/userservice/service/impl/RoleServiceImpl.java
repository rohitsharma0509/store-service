package com.app.ecom.store.userservice.service.impl;

import java.util.List;

import com.app.ecom.store.userservice.dto.IdsDto;
import com.app.ecom.store.userservice.dto.QueryRequest;
import com.app.ecom.store.userservice.dto.RoleDto;
import com.app.ecom.store.userservice.dto.RoleDtos;
import com.app.ecom.store.userservice.dto.RoleSearchRequest;
import com.app.ecom.store.userservice.handler.QueryHandler;
import com.app.ecom.store.userservice.mapper.RoleMapper;
import com.app.ecom.store.userservice.model.Role;
import com.app.ecom.store.userservice.repository.RoleRepository;
import com.app.ecom.store.userservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private QueryHandler<Role> queryHandler;

	@Override
	public RoleDto addUpdateRole(RoleDto roleDto) {
		return roleMapper.roleToRoleDto(roleRepository.save(roleMapper.roleDtoToRole(roleDto)));
	}

	@Override
	public RoleDtos getRoles(RoleSearchRequest roleSearchRequest) {
		queryHandler.setType(Role.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(roleMapper.roleSearchRequestToWhereClauses(roleSearchRequest));
		queryRequest.setOrderByClauses(roleSearchRequest.getOrderByClauses());
		queryRequest.setOffset(roleSearchRequest.getOffset());
		queryRequest.setLimit(roleSearchRequest.getLimit());
		List<Role> roles = queryHandler.findByQueryRequest(queryRequest);
		return roleMapper.rolesToRoleDtos(roles, true);
	}

	@Override
	public Long countRoles(RoleSearchRequest roleSearchRequest) {
		queryHandler.setType(Role.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(roleMapper.roleSearchRequestToWhereClauses(roleSearchRequest));
		return queryHandler.countByQueryRequest(queryRequest, "id");
	}

	@Override
	public void deleteRoles(IdsDto idsDto) {
		if (idsDto == null || CollectionUtils.isEmpty(idsDto.getIds())) {
			roleRepository.deleteAll();
		} else if (idsDto.getIds().size() == 1) {
			roleRepository.deleteById(idsDto.getIds().get(0));
		} else {
			roleRepository.deleteByIdIn(idsDto.getIds());
		}
	}
}
