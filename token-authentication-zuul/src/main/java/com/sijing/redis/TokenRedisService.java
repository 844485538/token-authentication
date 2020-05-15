package com.sijing.redis;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Token操作类
 * 
 * @author li
 *
 */
@Service
public class TokenRedisService {
	
	@Resource
    private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * 验证Token是否存在
	 * @param token
	 * @return
	 */
	public boolean checkToken(String token) {
		Object oldToken = redisTemplate.opsForValue().get(token);
		if (oldToken != null) {
			return true;
		}
		return false;
	}

}
