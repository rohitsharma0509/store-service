package com.app.ecom.store.controller;

import javax.servlet.http.HttpServletRequest;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.dto.usertokenservice.UserTokenDto;
import com.app.ecom.store.service.UserService;
import com.app.ecom.store.service.UserTokenService;
import com.app.ecom.store.service.impl.UserServiceImpl;
import com.app.ecom.store.validator.UserTokenValidator;
import com.app.ecom.store.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SignupController {
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private UserTokenService userTokenService;
    
    @Autowired
    private UserTokenValidator userTokenValidator;
    
    @GetMapping(value = RequestUrls.REGISTRATION)
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDto());
        return "registration";
    }

    @PostMapping(value = RequestUrls.REGISTRATION)
    public String registration(@ModelAttribute("userForm") UserDto userDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        UserDto createdUserDto = userService.createUser(userDto);
        try {
        	userService.sendVerificationLink(createdUserDto, request);
            model.addAttribute(FieldNames.MESSAGE, "Acount activation link has been sent to your email.");
        } catch (Exception e) {
        	model.addAttribute(FieldNames.ERROR, "Due to some technical problem, we are unable to send the email verification mail to activate your account. Please contact your administrator to activate your account.");
        	logger.error("Exception while sending verification mail." + e);
        }
        return "login";
    }
    
    @GetMapping(value = RequestUrls.REGISTRATION_CONFIRM)
    public String confirmRegistration(WebRequest request, Model model, BindingResult bindingResult, @RequestParam String token) {
        UserTokenDto userTokenDto = userTokenService.getUserToken(token);
        userTokenValidator.validate(userTokenDto, bindingResult);
        
        if(bindingResult.hasErrors()) {
        	model.addAttribute(FieldNames.ERROR, bindingResult.getAllErrors().get(0).getDefaultMessage());
            return RequestUrls.LOGIN;
        }
        
        UserDto userDto = userService.findUserById(userTokenDto.getUserId());
        userDto.setIsEnabled(true);
        userService.updateUser(userDto);
        model.addAttribute(FieldNames.MESSAGE, "Your acount has been activated now.");
        return RequestUrls.LOGIN; 
    }
}
