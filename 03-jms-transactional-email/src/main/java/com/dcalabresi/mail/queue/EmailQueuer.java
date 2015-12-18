package com.dcalabresi.mail.queue;

import com.dcalabresi.mail.model.EmailDto;
import com.dcalabresi.utils.MimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailQueuer {

	private Logger logger = LoggerFactory.getLogger(EmailQueuer.class);

	@Autowired
	JmsTemplate jmsTemplate;


	public void sendMail(String from, String fromName, String to, String unsubscribeUrl, String subject, String body,
						 MimeType mimeTypee, String fileBase64, String fileName) {
		EmailDto emailDto = new EmailDto(from, fromName, to, unsubscribeUrl, subject, body, mimeTypee, fileBase64, fileName);
		validateEmail(emailDto);
		jmsTemplate.convertAndSend(emailDto);
	}

	private void validateEmail(EmailDto emailDto) {
		// HERE SHOULD BE THE VALIDATIONS
	}

}