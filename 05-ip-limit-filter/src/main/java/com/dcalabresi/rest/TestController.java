package com.dcalabresi.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@RequestMapping(value="/", method= RequestMethod.GET)
	public String test() {
		return "Test";
	}

	@RequestMapping(value="/limited/", method= RequestMethod.GET)
	public String testLimited() {
		return "Test a limited URL by IP";
	}

}
