package com.app.ecom.store.template.resource;

import com.app.ecom.store.template.constants.Endpoint;
import com.app.ecom.store.template.dto.TemplateMailDto;
import com.app.ecom.store.template.service.EmailService;
import com.app.ecom.store.template.util.CommonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailResource {
	
	private static final Logger logger = LogManager.getLogger(EmailResource.class);
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@PostMapping(value = Endpoint.SEND_TEMPLATE_MAIL)
	public ResponseEntity<Void> sendMail(@RequestParam String locale,  @RequestBody TemplateMailDto templateMailDto) {
		try {
			emailService.sendTemplateMail(locale, templateMailDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("Exception while sending email: ").append(commonUtil.getStackTraceAsString(e)));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
