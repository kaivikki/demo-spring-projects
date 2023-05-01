package com.kaivikki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	MessageSource messageSource;

	@GetMapping(path = "/greetings")
	public String greet() {
		return messageSource.getMessage("good.morning.message", null, "Namaste", LocaleContextHolder.getLocale());
	}
	
}
