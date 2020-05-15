package com.sijing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sijing.service.UserRedisService;

@RestController
public class DemoController {
	
	@Autowired
	private UserRedisService userRedisService;
	
	@GetMapping("/login")
	public String login() {
		return userRedisService.createToken();
	}

}
