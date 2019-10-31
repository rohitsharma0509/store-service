package com.app.ecom.store.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.app.ecom.store.client.UserServiceClient;
import com.app.ecom.store.dto.CustomPage;
import com.app.ecom.store.dto.IdsDto;
import com.app.ecom.store.dto.userservice.PrivilegeDto;
import com.app.ecom.store.dto.userservice.PrivilegeDtos;
import com.app.ecom.store.dto.userservice.PrivilegeSearchRequest;
import com.app.ecom.store.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	
	@Autowired
	private UserServiceClient userServiceClient;

	@Override
	public PrivilegeDto getPrivilegeById(Long id) {
		PrivilegeSearchRequest privilegeSearchRequest = new PrivilegeSearchRequest();
		privilegeSearchRequest.setIds(Arrays.asList(id));
		PrivilegeDtos privilegeDtos = userServiceClient.getPrivileges(privilegeSearchRequest);
		return privilegeDtos == null || CollectionUtils.isEmpty(privilegeDtos.getPrivileges()) ? null : privilegeDtos.getPrivileges().get(0);
	}

	@Override
	public PrivilegeDto addPrivilege(PrivilegeDto privilegeDto) {
		return userServiceClient.createUpdatePrivilege(privilegeDto);
	}

	@Override
	public CustomPage<PrivilegeDto> getPrivileges(Pageable pageable, Map<String, String> params) {
		int offset = (pageable.getPageNumber() - 1)*pageable.getPageSize();
		int limit = offset + pageable.getPageSize();
		
		PrivilegeSearchRequest privilegeSearchRequest = new PrivilegeSearchRequest();
		privilegeSearchRequest.setName(params.get("name"));
		privilegeSearchRequest.setOffset(offset);
		privilegeSearchRequest.setLimit(limit);
		
		PrivilegeDtos privilegeDtos = userServiceClient.getPrivileges(privilegeSearchRequest);
		Long totalRecords = userServiceClient.countPrivileges(privilegeSearchRequest);
		CustomPage<PrivilegeDto> page = new CustomPage<>();
		page.setContent(privilegeDtos == null ? null : privilegeDtos.getPrivileges());
		page.setPageNumber(pageable.getPageNumber() - 1);
		page.setSize(pageable.getPageSize());
		page.setTotalPages((int)Math.ceil((double)totalRecords/pageable.getPageSize()));
		return page;
	}

	@Override
	public List<PrivilegeDto> getPrivilegesByIdIn(List<Long> ids) {
		PrivilegeSearchRequest privilegeSearchRequest = new PrivilegeSearchRequest();
		privilegeSearchRequest.setIds(ids);
		PrivilegeDtos privilegeDtos = userServiceClient.getPrivileges(privilegeSearchRequest);
		return privilegeDtos == null ? null : privilegeDtos.getPrivileges();
	}

	@Override
	public List<PrivilegeDto> getAllPrivileges() {
		PrivilegeDtos privilegeDtos = userServiceClient.getPrivileges(new PrivilegeSearchRequest());
		return privilegeDtos == null ? null : privilegeDtos.getPrivileges();
	}

	@Override
	public void deletePrivileges(IdsDto idsDto) {
		userServiceClient.deletePrivileges(idsDto);
	}
	
	@Override
	public boolean isRoleAssociatedWithPrivileges(List<Long> privilegeIds) {
		List<PrivilegeDto> privilegeDtos = getPrivilegesByIdIn(privilegeIds);
		return privilegeDtos.stream().anyMatch(p -> !CollectionUtils.isEmpty(p.getRoleDtos()));
	}
}