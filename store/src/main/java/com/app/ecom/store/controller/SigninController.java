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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class SigninController {
	
	private static final Logger logger = LogManager.getLogger(SigninController.class);
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private TemplateService templateService;
    
    @Autowired
    private UserTokenValidator userTokenValidator;
    
    @Autowired
    private UserTokenService userTokenService;
    
    @Autowired
    private Environment environment;
    
    @Autowired
    private CommonUtil commonUtil;
    
    @GetMapping(value = RequestUrls.LOGIN)
    public String login(Model model, String logout) {
        if (logout != null) {
            model.addAttribute(FieldNames.MESSAGE, environment.getProperty(ErrorCode.ERR000034.getCode()));
        }
        return View.LOGIN;
    }
    
    @GetMapping(value = RequestUrls.FORGET_PSWRD)
    public String redirectToForgetPswrd() {
        return View.FORGET_PSWRD;
    }
    
    @GetMapping(value = RequestUrls.SEND_RESET_PSWRD_LINK)
    public String sendResetPswrdLink(Model model, HttpServletRequest request, @RequestParam String email) {
    	UserDto userDto = userService.findUserByEmail(email);
    	if(userDto == null) {
    		model.addAttribute(FieldNames.ERROR, environment.getProperty(ErrorCode.ERR000032.getCode()));
    		return View.FORGET_PSWRD;
    	}
        TemplateMailDto templateMailDto = new TemplateMailDto();
        templateMailDto.setTrackingId(String.valueOf(userDto.getId()));
        templateMailDto.setTemplateName(MailTemplateName.PSWRD_RESET_TEMPLATE);
        templateMailDto.setTo(commonUtil.convertStringToArray(userDto.getEmail(), Constants.COMMA));
        Map<String, String> variables = new HashMap<>();
        variables.put(FieldNames.NAME, userDto.getFirstName());
        StringBuilder url = new StringBuilder(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString());
        String token = userTokenService.createUserToken(userDto);
    	url.append(request.getContextPath()).append(RequestUrls.RESET_PSWRD).append("?token=").append(token);
        variables.put(FieldNames.RESET_PSWRD_LINK, url.toString());
        templateMailDto.setVariables(variables);
        
        logger.info("sending mail to reset pswrd");
        templateService.sendTemplateMail(templateMailDto, request.getLocale());
        
        model.addAttribute(FieldNames.MESSAGE, environment.getProperty(ErrorCode.ERR000033.getCode()));
    	return View.LOGIN;
    }
    
    @GetMapping(value = RequestUrls.RESET_PSWRD)
    public String redirectToResetPswrd(Model model, @RequestParam String token) {
    	UserTokenDto userTokenDto = userTokenService.getUserToken(token);
        Response response = userTokenValidator.validateToken(userTokenDto);
        if(response.getCode() != HttpStatus.OK.value()) {
        	model.addAttribute(FieldNames.ERROR, response.getDescription());
            return RequestUrls.LOGIN;
        }
        model.addAttribute(FieldNames.USER_ID, userTokenDto.getUserId());
    	return View.RESET_PSWRD;
    }
    
    @PostMapping(value = RequestUrls.CHANGE_CRED)
	public String changePswrd(Model model, @RequestParam String pswrd, @RequestParam String confirmPswrd, @RequestParam Long userId) {
		Response response = userValidator.validatePassword(pswrd, confirmPswrd);
		if(HttpStatus.OK.value() == response.getCode()) {
			boolean flag = userService.changePassword(userId, pswrd);
			if(flag) {
				model.addAttribute(FieldNames.MESSAGE, environment.getProperty(ErrorCode.ERR000029.getCode()));
				return View.LOGIN;
			} else {
				model.addAttribute(FieldNames.ERROR, environment.getProperty(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())));
			}
		} else {
			model.addAttribute(FieldNames.ERROR, response.getDescription());
		}
		return View.RESET_PSWRD;
	}
}
