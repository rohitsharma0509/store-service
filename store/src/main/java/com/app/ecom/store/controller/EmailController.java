package com.app.ecom.store.controller;

import javax.validation.Valid;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.Email;
import com.app.ecom.store.service.EmailService;
import com.app.ecom.store.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private EmailValidator emailValidator;
	
	@GetMapping(value = RequestUrls.COMPOSE_EMAIL)
	public String composeEmail(Model model) {
		model.addAttribute(FieldNames.EMAIL, new Email());
		return View.COMPOSE_EMAIL;
	}
	
	@PostMapping(value = RequestUrls.SEND_EMAIL)
	public String sendEmail(Model model, @ModelAttribute(FieldNames.EMAIL)  @Valid Email email, BindingResult bindingResult) {
		emailValidator.validate(email, bindingResult);
		if (bindingResult.hasErrors()) {
			return View.COMPOSE_EMAIL;
		}
		emailService.sendEmail(email);
		return View.ADMIN;
	}
}