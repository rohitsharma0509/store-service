package com.app.ecom.store.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.ecom.store.constants.Constants;
import com.app.ecom.store.constants.FieldNames;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, 
      HttpServletResponse response, AuthenticationException exception) throws IOException {
        String errorMessage = Constants.EMPTY_STRING;
        if(exception instanceof DisabledException) {
            errorMessage = "Your account has been disabled. Please contact your adminstrator to activate it.";
        } else if (exception instanceof BadCredentialsException) {
            errorMessage = "Your username or password is invalid.";
        }
        request.getSession().setAttribute(FieldNames.ERROR, errorMessage);
        response.sendRedirect("login");
    }
}
