package com.app.ecom.store.exceptions;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@PropertySource("classpath:errorMessages.properties")
public class CustomExceptionHandler {
	
	private static final Logger logger = LogManager.getLogger(CustomExceptionHandler.class);
	
	@Autowired
	private Environment environment;
	
	@ExceptionHandler(Exception.class)
	public String processException(Model model, Exception exception) {
		logger.error(new StringBuilder("Exception while processing your request: ").append(exception.getMessage()));
		model.addAttribute(FieldNames.MESSAGE, environment.getProperty(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())));		
		return "redirect:"+RequestUrls.FAILURE+"/"+HttpStatus.INTERNAL_SERVER_ERROR.value();
	}
}
