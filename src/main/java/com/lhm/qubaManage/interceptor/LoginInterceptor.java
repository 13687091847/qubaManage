package com.lhm.qubaManage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lhm.qubaManage.common.CommonConstant;
import com.lhm.qubaManage.util.MainUtil;
import com.lhm.qubaManage.util.RedisUtil;

/**  
 * 登录拦截器
 * @package: com.lhm.qubaManage.interceptor
 * @author: liu huangming
 * @date: 2019年12月27日 下午1:40:37 
 */
@Component
@SuppressWarnings({"rawtypes","unchecked"})
public class LoginInterceptor implements HandlerInterceptor{
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		logger.info("进入拦截器");
//		RedisUtil redisUtil = new RedisUtil();
//		redisUtil.setRedisTemplate(redisTemplate);
//		String ip = MainUtil.getIpAddr(request);
//		String jsonString = (String)redisUtil.get(String.format(CommonConstant.REDIS_LOGIN_INFO, ip));
//		if (StringUtils.isBlank(jsonString)) {
//			// 登录已过期
//			response.sendRedirect("/login.htm");
//			logger.info("登录已经过期");
//			return false;
//		}else {
//			logger.info("登录还未过期");
//			return true;
//		}
		return true;
    }
}
