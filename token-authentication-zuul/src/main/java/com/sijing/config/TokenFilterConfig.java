package com.sijing.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * zuul 过滤器
 * 
 * @author li
 *
 */
@Configuration
public class TokenFilterConfig extends ZuulFilter {
	
	private final static Logger logger = LoggerFactory.getLogger(TokenFilterConfig.class);
	
	/**
	 * 白名单
	 */
	private static List<String> whitelisted = new ArrayList<>();
	
	/**
	 * 设置白名单
	 */
	static {
		
	}
	
	/**
	 * 判断是否执行此过滤器
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}
	
	/**
	 * 设置此过滤器的执行时机
	 */
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}
	
	/**
	 * 同filterType()下的优先级
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public Object run() throws ZuulException {
		
		// 获取上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        
        try {
            String token = request.getHeader("Authentication-Token");
            String userAgentSource = request.getHeader("User-Agent");
            
            PathMatcher matcher = new AntPathMatcher();
            String requestURI = request.getRequestURI();
            // 设置返回内容格式 UTF-8
            requestContext.getResponse().setCharacterEncoding("UTF-8");
            
            // 验证请求是否在白名单中
            for (String patternUrl : whitelisted) {
                if (matcher.match(patternUrl, requestURI)) {
                    requestContext.setSendZuulResponse(true);
                    return null;
                }
            }
            
            // 存在token即放行,在服务中自行验证token的正确性
            if (StringUtils.isNotEmpty(token)){
            	requestContext.setSendZuulResponse(true);
                return null;
            } else {
            	// 缺失token,设置401
            	requestContext.setResponseStatusCode(401);
            	requestContext.setSendZuulResponse(false);
            }

        } catch (Exception e) {
        	logger.info("Zuul gateway throw an exception, Exception info: {}", e.getMessage());
            // 发生异常的情况下,响应码500，服务器内部处理出现异常
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            // 设置跨域响应头
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Allow-Headers", "*");
            // 拦截接口
            requestContext.setSendZuulResponse(false);
        }
        return null;
	}



}
