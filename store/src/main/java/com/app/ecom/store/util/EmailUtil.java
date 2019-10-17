package com.app.ecom.store.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	private static final Logger logger = LogManager.getLogger(EmailUtil.class);

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String[] to, String[] cc, String[] bcc, String subject, String body) {
		try {
			logger.info(new StringBuilder("Sending mail to:").append(to).append("; cc:").append(cc).append("; bcc:")
					.append(cc).append("; with subject: ").append(subject).toString());
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			if(null != to && to.length>0){
				helper.setTo(to);
			}
			if(null != cc && cc.length>0){
				helper.setCc(cc);
			}
			if(null != bcc && bcc.length>0){
				helper.setBcc(bcc);
			}
			helper.setTo(String.join(",", to));
			helper.setText(body, true);
			helper.setSubject(subject);
			javaMailSender.send(message);
			logger.info("Mail sent successfully");
		} catch (MessagingException e) {
			logger.error("MessagingException while sending mail: "+e);
		}
	}

}
