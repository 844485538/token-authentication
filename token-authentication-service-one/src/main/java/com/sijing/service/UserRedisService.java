package com.sijing.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserRedisService {
	
	@Resource
    private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * create token
	 * @return
	 */
	public String createToken() {
		
		String uuid = UUID.randomUUID().toString()
				.toUpperCase()
				.replaceAll("-", "");
		
		redisTemplate.opsForValue().set("user", uuid);
		return uuid;
	}

}
