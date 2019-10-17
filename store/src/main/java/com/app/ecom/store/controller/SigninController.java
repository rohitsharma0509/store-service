package com.app.ecom.store.controller;

import javax.servlet.http.HttpServletRequest;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.dto.UserDto;
import com.app.ecom.store.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SigninController {
	
	private static final Logger logger = LogManager.getLogger(SigninController.class);
	
    @Autowired
    private UserService userService;
    
    @GetMapping(value = RequestUrls.LOGIN)
    public String login(Model model, String logout) {
        if (logout != null) {
            model.addAttribute(FieldNames.MESSAGE, "You have been logged out successfully.");
        }
        return "login";
    }
    
    @GetMapping(value = RequestUrls.FORGET_PASSWORD)
    public String forgetPassword() {
        return "forgetPassword";
    }
    
    @GetMapping(value = RequestUrls.CHANGE_PASSWORD)
    public String changePassword(Model model, HttpServletRequest request, @RequestParam String email) {
    	UserDto userDto = userService.findUserByEmail(email);
    	try {
        	userService.sendChangePasswordLink(userDto, request);
        	model.addAttribute(FieldNames.MESSAGE, "We have emailed you a change password link to your email address. When you receive the email, click the link to enter a new password.");
        } catch (Exception e) {
        	model.addAttribute(FieldNames.ERROR, "Due to some technical problem, we are unable to send change password link to your email. Please try after some time.");
        	logger.error("Exception while sending change password mail." + e);
        	return "login";
        }
    	return "changePassword";
    }
    
    @GetMapping(value = RequestUrls.RESET_PASSWORD)
    public String changePassword(Model model, @RequestParam String email) {
    	return "resetPassword";
    }
}
