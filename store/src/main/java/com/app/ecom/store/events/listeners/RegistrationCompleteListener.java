package com.app.ecom.store.events.listeners;

import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.events.RegistrationCompleteEvent;
import com.app.ecom.store.service.UserTokenService;
import com.app.ecom.store.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationCompleteListener implements ApplicationListener<RegistrationCompleteEvent> {
    
    @Autowired
    private EmailUtil emailUtil;
    
    @Autowired
    private UserTokenService userTokenService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        String token = userTokenService.createUserToken(event.getUserDto());
        StringBuilder confirmationUrl = new StringBuilder(event.getUrl()).append(RequestUrls.REGISTRATION_CONFIRM).append("?token=").append(token);
        emailUtil.sendEmail(new String[] {event.getUserDto().getEmail()}, null, null, "Registration Confirmation", confirmationUrl.toString());
    }
}
