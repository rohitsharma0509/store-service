package com.app.ecom.store.service.impl;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.dto.Email;
import com.app.ecom.store.service.EmailService;
import com.app.ecom.store.util.CommonUtil;
import com.app.ecom.store.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private EmailUtil emailUtil;

	@Override
	public void sendEmail(Email email) {
		String[] to = commonUtil.convertStringToArray(email.getTo(), Constants.COMMA);
		String[] cc = commonUtil.convertStringToArray(email.getCc(), Constants.COMMA);
		String[] bcc = commonUtil.convertStringToArray(email.getBcc(), Constants.COMMA);
		emailUtil.sendEmail(to, cc, bcc, email.getSubject(), email.getBody());
	}
}
