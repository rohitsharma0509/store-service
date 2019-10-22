package com.app.ecom.store.userservice.mapper;

import com.app.ecom.store.userservice.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
	
	private static final Logger logger = LogManager.getLogger(UserMapper.class);
	
	@Autowired
	private CommonUtil commonUtil;
	
}
