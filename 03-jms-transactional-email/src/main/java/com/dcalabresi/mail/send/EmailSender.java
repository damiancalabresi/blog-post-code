package com.dcalabresi.mail.send;

import com.dcalabresi.mail.model.EmailDto;
import com.dcalabresi.mail.model.EmailStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

	private Logger logger = LoggerFactory.getLogger(EmailSender.class);

	public EmailStatus sendEmail(EmailDto emailDto) {
		logger.info("Sending an email - This sender only writes to the standard output");
		System.out.println("SEND EMAIL:");
		System.out.print(emailDto.toString());
		return new EmailStatus(emailDto.getTo(), "OK", "");
	}

}
