package com.sijing.redis;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
	 * 验证Token是否正确
	 * @param token
	 * @return
	 */
	public boolean checkToken(String token) {
		if (StringUtils.isEmpty(token)) {
			return false;
		}
		Object userId = redisTemplate.opsForValue().get("user:toekn:" + token);
		if (userId != null) {
			return true;
		}
		return false;
	}

}
