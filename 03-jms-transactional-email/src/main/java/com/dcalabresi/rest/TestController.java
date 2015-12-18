package com.dcalabresi.rest;

import com.dcalabresi.mail.queue.EmailQueuer;
import com.dcalabresi.utils.MimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	private Random random = new Random();

	@Autowired
	EmailQueuer emailQueuer;
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String test() {
		String text = "Mail number: " + random.nextInt(100);
		emailQueuer.sendMail("from@dcalabresi.com", "Dcalabresi", "to@dcalabresi.com", "",
				"A Subject", text, MimeType.TEXT, null, null);
		return text;
	}

}
