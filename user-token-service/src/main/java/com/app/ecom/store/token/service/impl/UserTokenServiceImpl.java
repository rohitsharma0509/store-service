package com.app.ecom.store.token.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.ecom.store.token.dto.QueryRequest;
import com.app.ecom.store.token.dto.UserTokenDto;
import com.app.ecom.store.token.dto.WhereClause;
import com.app.ecom.store.token.enums.OperationType;
import com.app.ecom.store.token.handler.QueryHandler;
import com.app.ecom.store.token.mapper.UserTokenMapper;
import com.app.ecom.store.token.model.UserToken;
import com.app.ecom.store.token.repository.UserTokenRepository;
import com.app.ecom.store.token.service.UserTokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class UserTokenServiceImpl implements UserTokenService {
	
	private static final Logger logger = LogManager.getLogger(UserTokenServiceImpl.class);
	
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
