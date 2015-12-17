package com.dcalabresi.mail.send;

import com.dcalabresi.mail.model.EmailDto;
import com.dcalabresi.mail.model.EmailStatus;
import com.dcalabresi.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class EmailDequeuer {

	private Logger logger = LoggerFactory.getLogger(EmailDequeuer.class);

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	EmailSender emailSender;

	@Transactional(transactionManager = Constants.TRANSACTION_MANAGER_JMS)
	public void sendEnqueuedEmails() {
		while (sendEmailInQueue()) {}
	}

	private boolean sendEmailInQueue() {
		Object received = jmsTemplate.receiveAndConvert();
		if (received == null) {
			return false;
		} else if (received instanceof EmailDto) {
			EmailDto emailDto = (EmailDto) received;
			logger.info("Sending email from [" + emailDto.getFrom() + "] to [" + emailDto.getTo() + "] subject [" + emailDto.getSubject() + "]");
			EmailStatus emailStatus = emailSender.sendEmail(emailDto);
			return true;
		} else {
			logger.error("The mail has not the corresponding format - class: " + received.getClass().toString());
			return true;
		}
	}

}
