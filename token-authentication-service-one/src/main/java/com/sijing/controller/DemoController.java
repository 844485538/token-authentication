package com.sijing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@GetMapping("/getServerName")
	public String getServerName() {
		return "token-authentication-one";
	}

}
