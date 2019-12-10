package com.app.ecom.store.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.app.ecom.store.client.UserServiceClient;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.userservice.PrivilegeDto;
import com.app.ecom.store.dto.userservice.RoleDto;
import com.app.ecom.store.dto.userservice.RoleDtos;
import com.app.ecom.store.dto.userservice.RoleSearchRequest;
import com.app.ecom.store.service.PrivilegeService;
import com.app.ecom.store.service.RoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private UserServiceClient userServiceClient;
	
	@Autowired
	private PrivilegeService privilegeService;
	
    @Override
	public CustomPage<RoleDto> getRoles(Pageable pageable, Map<String, String> params) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		
		RoleSearchRequest roleSearchRequest = new RoleSearchRequest();
		roleSearchRequest.setRoleName(params.get(FieldNames.ROLE_NAME));
		roleSearchRequest.setUserName(params.get(FieldNames.NAME));
		roleSearchRequest.setOffset(offset);
		roleSearchRequest.setLimit(limit);
		RoleDtos roleDtos = userServiceClient.getRoles(roleSearchRequest);
		Long totalRecords = userServiceClient.countRoles(roleSearchRequest);
		CustomPage<RoleDto> page = new CustomPage<>();
		page.setContent(roleDtos == null ? null : roleDtos.getRoles());
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
		return userServiceClient.createUpdateRole(roleDto);
	}
	
	@Override
	public RoleDto getRoleByName(String name) {
		RoleSearchRequest roleSearchRequest = new RoleSearchRequest();
		roleSearchRequest.setRoleName(name);
		RoleDtos roleDtos = userServiceClient.getRoles(roleSearchRequest);
		return roleDtos == null || CollectionUtils.isEmpty(roleDtos.getRoles()) ? null : roleDtos.getRoles().get(0);
	}

	@Override
	public RoleDto getRoleById(Long id) {
		RoleSearchRequest roleSearchRequest = new RoleSearchRequest();
		roleSearchRequest.setRoleIds(Arrays.asList(id));
		RoleDtos roleDtos = userServiceClient.getRoles(roleSearchRequest);
		return roleDtos == null || CollectionUtils.isEmpty(roleDtos.getRoles()) ? null : roleDtos.getRoles().get(0);
	}
	
	@Override
	public void deleteRoles(IdsDto idsDto) {
		userServiceClient.deleteRoles(idsDto);
	}

	@Override
	public List<RoleDto> getRolesByIdIn(List<Long> ids) {
		RoleSearchRequest roleSearchRequest = new RoleSearchRequest();
		roleSearchRequest.setRoleIds(ids);
		RoleDtos roleDtos = userServiceClient.getRoles(roleSearchRequest);
		return roleDtos == null ? null : roleDtos.getRoles();
	}

	@Override
	public List<RoleDto> getAllRoles() {
		RoleDtos roleDtos = userServiceClient.getRoles(new RoleSearchRequest());
		return roleDtos == null ? null : roleDtos.getRoles();
	}
}
