package com.app.ecom.store.events;

import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.service.UserTokenService;
import com.app.ecom.store.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordListener implements ApplicationListener<ChangePasswordEvent> {

	@Autowired
	private EmailUtil emailUtil;

	@Autowired
	private UserTokenService userTokenService;

	@Override
	public void onApplicationEvent(ChangePasswordEvent event) {
		String token = userTokenService.createUserToken(event.getUserDto());
		StringBuilder confirmationUrl = new StringBuilder(event.getUrl()).append(RequestUrls.RESET_PASSWORD).append("?token=").append(token);
		emailUtil.sendEmail(new String[] { event.getUserDto().getEmail() }, null, null, "Change your password", confirmationUrl.toString());
	}
}
