package com.sijing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sijing.service.UserRedisService;

@RestController
public class DemoController {
	
	@Autowired
	private UserRedisService userRedisService;
	
	/**
	 * Redis
	 * 		
	 * 		key:user
	 * 		value:token
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		return userRedisService.createToken();
	}
	
	
	@GetMapping("/getInfo")
	public String getInfo() {
		return "welcome to sijing";
	}

}
