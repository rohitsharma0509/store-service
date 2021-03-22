package com.app.ecom.store.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.app.ecom.store.userservice.dto.QueryRequest;
import com.app.ecom.store.userservice.dto.UserTokenDto;
import com.app.ecom.store.userservice.dto.WhereClause;
import com.app.ecom.store.userservice.enums.OperationType;
import com.app.ecom.store.userservice.handler.QueryHandler;
import com.app.ecom.store.userservice.mapper.UserTokenMapper;
import com.app.ecom.store.userservice.model.UserToken;
import com.app.ecom.store.userservice.repository.UserTokenRepository;
import com.app.ecom.store.userservice.service.UserTokenService;

@Service
public class UserTokenServiceImpl implements UserTokenService {
	
	@Autowired
	private UserTokenRepository userTokenRepository;
	
	@Autowired
	private UserTokenMapper userTokenMapper;
	
	@Autowired
	private QueryHandler<UserToken> queryHandler;

	@Override
	public UserTokenDto createUpdateToken(UserTokenDto userTokenDto) {
        return userTokenMapper.userTokenToUserTokenDto(userTokenRepository.save(userTokenMapper.userTokenDtoToUserToken(userTokenDto)));
	}

	@Override
	public UserTokenDto getToken(String token) {
		queryHandler.setType(UserToken.class);
		QueryRequest queryRequest = new QueryRequest();
		List<WhereClause> whereClauses = new ArrayList<>();
		WhereClause whereClause = new WhereClause("token", token, OperationType.EQUALS);
		whereClauses.add(whereClause);
		queryRequest.setWhereClauses(whereClauses);
		List<UserToken> tokens = queryHandler.findByQueryRequest(queryRequest);
		return userTokenMapper.userTokenToUserTokenDto(CollectionUtils.isEmpty(tokens) ? null : tokens.get(0));
	}
}
