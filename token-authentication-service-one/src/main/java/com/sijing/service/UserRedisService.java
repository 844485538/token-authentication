package com.sijing.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class UserRedisService {
	
	@Resource
    private RedisTemplate<String, Object> redisTemplate;
	
	private final static Long USER_ID = 1000L;
	
	/**
	 * create token
	 * @return
	 */
	public String createToken() {
		
		String uuid = UUID.randomUUID().toString()
				.toUpperCase()
				.replaceAll("-", "");
		
		redisTemplate.opsForValue().set("user:id:" + USER_ID, uuid);
		redisTemplate.opsForValue().set("user:toekn:" + uuid, USER_ID);
		return uuid;
	}

}
