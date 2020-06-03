package com.app.ecom.store.template.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.app.ecom.store.template.dto.EmailDto;
import com.app.ecom.store.template.dto.TemplateMailDto;
import com.app.ecom.store.template.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${email.default-from-email}")
	private String defaultFromEmail;
	
	
	@Override
	public void sendMail(EmailDto emailDto, String locale) throws MessagingException {
		Context context = new Context(Locale.forLanguageTag(locale));
		
		if(!CollectionUtils.isEmpty(emailDto.getVariables())) {
			for(Map.Entry<String, Object> entry : emailDto.getVariables().entrySet()) {
				context.setVariable(entry.getKey(), entry.getValue());
			}
		}
		
		final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(emailDto.getSubject());
	    String fromEmail = emailDto.getFrom();
	    if(StringUtils.isEmpty(fromEmail)) {
	    	fromEmail = defaultFromEmail;
	    }
	    message.setFrom(fromEmail);
	    message.setTo(emailDto.getTo());
	    message.setText(emailDto.getBody(), true);
		javaMailSender.send(mimeMessage);
	}
	
	@Async
	@Override
	public void sendTemplateMail(String locale, TemplateMailDto templateMailDto) throws MessagingException {
		Context context = new Context(Locale.forLanguageTag(locale));
		
		if(!CollectionUtils.isEmpty(templateMailDto.getVariables())) {
			for(Map.Entry<String, Object> entry : templateMailDto.getVariables().entrySet()) {
				context.setVariable(entry.getKey(), entry.getValue());
			}
		}
		
		final String htmlContent = templateEngine.process(templateMailDto.getTemplateName().getName(), context);
		
		final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
	    message.setSubject(templateMailDto.getTemplateName().getSubject());
	    String fromEmail = templateMailDto.getFrom();
	    if(StringUtils.isEmpty(fromEmail)) {
	    	fromEmail = defaultFromEmail;
	    }
	    message.setFrom(fromEmail);
	    message.setTo(templateMailDto.getTo());
	    message.setText(htmlContent, true);
		javaMailSender.send(mimeMessage);
	}

}
