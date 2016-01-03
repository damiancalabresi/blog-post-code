package com.dcalabresi.rest;

import com.dcalabresi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	TestService testService;

	@RequestMapping(value="/{valuePublic}/{valueSecret}", method= RequestMethod.GET)
	public String test(@PathVariable String valuePublic, @PathVariable String valueSecret) {
		return testService.test(valuePublic, valueSecret);
	}

}
