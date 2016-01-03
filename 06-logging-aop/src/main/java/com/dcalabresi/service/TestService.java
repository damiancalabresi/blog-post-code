package com.dcalabresi.service;

import com.dcalabresi.aop.log.Loggable;
import com.dcalabresi.aop.log.NotLog;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Service
public class TestService {

	@Loggable
	public String test(String user, @NotLog String password) {
		return "Test - Arguments received - User: " + user + " - Password: " + password;
	}

}
