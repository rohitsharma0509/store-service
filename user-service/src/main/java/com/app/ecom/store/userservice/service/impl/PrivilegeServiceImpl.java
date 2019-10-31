package com.app.ecom.store.userservice.service.impl;

import java.util.List;

import com.app.ecom.store.userservice.dto.IdsDto;
import com.app.ecom.store.userservice.dto.PrivilegeDto;
import com.app.ecom.store.userservice.dto.PrivilegeDtos;
import com.app.ecom.store.userservice.dto.PrivilegeSearchRequest;
import com.app.ecom.store.userservice.dto.QueryRequest;
import com.app.ecom.store.userservice.handler.QueryHandler;
import com.app.ecom.store.userservice.mapper.PrivilegeMapper;
import com.app.ecom.store.userservice.model.Privilege;
import com.app.ecom.store.userservice.repository.PrivilegeRepository;
import com.app.ecom.store.userservice.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private PrivilegeMapper privilegeMapper;

	@Autowired
	private QueryHandler<Privilege> queryHandler;
	
	//@Autowired
	//private CacheManager cacheManager;

	@Override
	@Transactional
	public PrivilegeDto addUpdatePrivilege(PrivilegeDto privilegeDto) {
		Privilege privilege = privilegeRepository.save(privilegeMapper.privilegeDtoToPrivilege(privilegeDto));
		//cacheManager.getCache("privilegesCache").clear();
		return privilegeMapper.privilegeToPrivilegeDto(privilege);
	}

	@Override
	//@Cacheable(value = "privilegesCache", key = "{ #privilegeSearchRequest.getId(), #privilegeSearchRequest.getName()}")
	public PrivilegeDtos getPrivileges(PrivilegeSearchRequest privilegeSearchRequest) {
		queryHandler.setType(Privilege.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(privilegeMapper.privilegeSearchRequestToWhereClauses(privilegeSearchRequest));
		queryRequest.setOrderByClauses(privilegeSearchRequest.getOrderByClauses());
		queryRequest.setOffset(privilegeSearchRequest.getOffset());
		queryRequest.setLimit(privilegeSearchRequest.getLimit());
		List<Privilege> privileges = queryHandler.findByQueryRequest(queryRequest);
		return privilegeMapper.privilegesToPrivilegeDtos(privileges);
	}
	
	@Override
	public Long countPrivileges(PrivilegeSearchRequest privilegeSearchRequest) {
		queryHandler.setType(Privilege.class);
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setWhereClauses(privilegeMapper.privilegeSearchRequestToWhereClauses(privilegeSearchRequest));
		return queryHandler.countByQueryRequest(queryRequest, "id");
	}

	@Override
	@Transactional
	public void deletePrivileges(IdsDto idsDto) {
		if (idsDto == null || CollectionUtils.isEmpty(idsDto.getIds())) {
			privilegeRepository.deleteAll();
		} else if (idsDto.getIds().size() == 1) {
			privilegeRepository.deleteById(idsDto.getIds().get(0));
		} else {
			privilegeRepository.deleteByIdIn(idsDto.getIds());
		}
		//cacheManager.getCache("privilegesCache").clear();
	}
}
