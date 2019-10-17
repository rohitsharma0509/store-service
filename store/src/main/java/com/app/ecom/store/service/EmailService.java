package com.app.ecom.store.service;

import com.app.ecom.store.dto.Email;
import com.app.ecom.store.model.EmailAccount;

public interface EmailService {
	EmailAccount getEmailAccount();

	EmailAccount getEmailAccountById(Long id);

	EmailAccount addEmailAccount(EmailAccount emailAccount);
	
	void sendEmail(Email email);
}
