package com.sijing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TokenAuthenticationServiceOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenAuthenticationServiceOneApplication.class, args);
	}

}
