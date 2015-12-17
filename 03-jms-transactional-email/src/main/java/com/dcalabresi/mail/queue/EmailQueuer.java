package com.dcalabresi.mail.queue;

import com.dcalabresi.mail.model.EmailDto;
import com.dcalabresi.utils.MimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
		if (emailDto.getTo() == null || emailDto.getTo().isEmpty()) {
			logEmailValidationError("No target emails");
			throw new RuntimeException("EmailToAddressIncorrect");
		}

		for (String dest : emailDto.getTo()) {
			if (dest == null || dest.isEmpty() || validateEmailAddress(dest) == false) {
				logEmailValidationError("Invalid email (to): " + dest);
				throw new RuntimeException("EmailToAddressIncorrect");
			}
		}

		if (emailDto.getFileBase64() != null) {
			if (emailDto.getFileBase64().length() > MAX_CHAR_BASE64_ATTACHED_FILE) {
				throw new EmailAttachmentLimitExceeded();
			}
			if (emailDto.getFileName() == null || emailDto.getFileName().isEmpty()) {
				logEmailValidationError("There is an attachment file, but no name for it!");
				throw new EmailAttachmentFileNameIsEmpty();
			}
		}

		if (emailDto.getFrom() == null || emailDto.getFrom().isEmpty() || !validateEmailAddress(emailDto.getFrom())) {
			logEmailValidationError("Invalid email (from): " + emailDto.getFrom());
			throw new EmailFromAddressEmpty();
		}

		if (emailDto.getSubject() == null || emailDto.getSubject().isEmpty()) {
			logEmailValidationError("The email does not have a subject");
			throw new EmailSubjectEmpty();
		}

		if (emailDto.getBody() == null || emailDto.getBody().isEmpty()) {
			logEmailValidationError("The email does not have a body");
			throw new EmailBodyEmpty();
		}

	}

	private boolean validateEmailAddress(String email) {
		try {
			new InternetAddress(email).validate();
		} catch (AddressException ex) {
			return false;
		}
		return true;
	}

	private void logEmailValidationError(String message) {
		logger.error("Email validation error - It will be moved to failed messages queue - error: " + message);
	}

}