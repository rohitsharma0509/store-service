package com.app.ecom.store.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.constants.View;
import com.app.ecom.store.dto.Response;
import com.app.ecom.store.dto.templateservice.TemplateMailDto;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.dto.usertokenservice.UserTokenDto;
import com.app.ecom.store.enums.ErrorCode;
import com.app.ecom.store.enums.MailTemplateName;
import com.app.ecom.store.service.TemplateService;
import com.app.ecom.store.service.UserService;
import com.app.ecom.store.service.UserTokenService;
import com.app.ecom.store.service.impl.UserServiceImpl;
import com.app.ecom.store.util.CommonUtil;
import com.app.ecom.store.validator.UserTokenValidator;
import com.app.ecom.store.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    
    @Autowired
    private Environment environment;
    
    @Autowired
    private CommonUtil commonUtil;
    
    @Autowired
    private TemplateService templateService;
    
    @GetMapping(value = RequestUrls.REGISTRATION)
    public String registration(Model model) {
        model.addAttribute(FieldNames.USER_DTO, new UserDto());
        return View.REGISTRATION;
    }

    @PostMapping(value = RequestUrls.REGISTRATION)
    public String registration(@ModelAttribute(FieldNames.USER_DTO) UserDto userDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return View.REGISTRATION;
        }
        UserDto createdUserDto = userService.createUser(userDto);
        
        TemplateMailDto templateMailDto = new TemplateMailDto();
        templateMailDto.setTrackingId(String.valueOf(createdUserDto.getId()));
        templateMailDto.setTemplateName(MailTemplateName.USER_REGISTERED_TEMPLATE);
        templateMailDto.setTo(commonUtil.convertStringToArray(createdUserDto.getEmail(), Constants.COMMA));
        Map<String, String> variables = new HashMap<>();
        variables.put(FieldNames.NAME, createdUserDto.getFirstName());
        StringBuilder url = new StringBuilder(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString());
        String token = userTokenService.createUserToken(createdUserDto);
    	url.append(request.getContextPath()).append(RequestUrls.REGISTRATION_CONFIRM).append("?token=").append(token);
        variables.put(FieldNames.ACCOUNT_ACTIVATION_LINK, url.toString());
        templateMailDto.setVariables(variables);
        
        logger.info("sending mail to activate user account");
        templateService.sendTemplateMail(templateMailDto, request.getLocale());
        
        model.addAttribute(FieldNames.MESSAGE, environment.getProperty(ErrorCode.ERR000035.getCode()));
        return View.LOGIN;
    }
    
    @GetMapping(value = RequestUrls.REGISTRATION_CONFIRM)
    public String confirmRegistration(WebRequest request, Model model, @RequestParam String token) {
        UserTokenDto userTokenDto = userTokenService.getUserToken(token);
        Response response = userTokenValidator.validateToken(userTokenDto);
        
        if(response.getCode() != HttpStatus.OK.value()) {
        	model.addAttribute(FieldNames.ERROR, response.getDescription());
            return RequestUrls.LOGIN;
        }
        
        UserDto userDto = userService.findUserById(userTokenDto.getUserId());
        userDto.setIsEnabled(true);
        userDto.setLastModifiedBy(userDto.getUsername());
        userService.updateUser(userDto);
        model.addAttribute(FieldNames.MESSAGE, environment.getProperty(ErrorCode.ERR000036.getCode()));
        return RequestUrls.LOGIN; 
    }
}
