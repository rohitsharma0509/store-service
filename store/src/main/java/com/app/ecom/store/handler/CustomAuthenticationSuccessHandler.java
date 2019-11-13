package com.app.ecom.store.handler;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private UserService userService;
    
    @Autowired
    private HttpSession httpSession;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        setLocale(authentication, request, response);
        response.sendRedirect(RequestUrls.HOME);
    }

    protected void setLocale(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        if (null != authentication && null != authentication.getPrincipal()) {
        	String username = authentication.getName();
            UserDto userDto = userService.findUserByUsername(username);
            httpSession.setAttribute(FieldNames.USER, userDto);
            String locale = null == userDto || StringUtils.isEmpty(userDto.getLanguage()) ? Locale.ENGLISH.getLanguage() : userDto.getLanguage();
            userService.updateLocale(request, response, locale);
        }
    }
}