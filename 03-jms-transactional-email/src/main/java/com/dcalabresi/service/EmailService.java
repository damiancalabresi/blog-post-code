package com.dcalabresi.service;

import com.dcalabresi.mail.queue.EmailQueuer;
import com.dcalabresi.utils.Constants;
import com.dcalabresi.utils.MimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class EmailService {

	private Logger logger = LoggerFactory.getLogger(EmailService.class);

	private Random random = new Random();

	@Autowired
	EmailQueuer emailQueuer;

	@Transactional(transactionManager = Constants.TRANSACTION_MANAGER_JMS)
	public String sendExampleEmail() {
		String text = "Mail number: " + random.nextInt(100);
		emailQueuer.sendMail("from@dcalabresi.com", "Dcalabresi", "to@dcalabresi.com", "",
				"A Subject", text, MimeType.TEXT, null, null);
		return text;
	}

}