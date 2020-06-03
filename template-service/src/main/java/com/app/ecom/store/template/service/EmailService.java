package com.app.ecom.store.template.service;

import javax.mail.MessagingException;

import com.app.ecom.store.template.dto.EmailDto;
import com.app.ecom.store.template.dto.TemplateMailDto;

public interface EmailService {
	
	void sendMail(EmailDto emailDto, String locale) throws MessagingException;
	
	void sendTemplateMail(String locale, TemplateMailDto templateMailDto) throws MessagingException;

}
