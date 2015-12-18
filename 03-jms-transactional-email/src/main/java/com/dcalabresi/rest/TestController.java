package com.dcalabresi.rest;

import com.dcalabresi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	EmailService emailService;
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String test() {
		return emailService.sendExampleEmail();
	}

}
