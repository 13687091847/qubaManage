package com.lhm.qubaManage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.lhm.qubaManage.common.CommonConstant;
import com.lhm.qubaManage.entity.ManageUser;
import com.lhm.qubaManage.util.MainUtil;
import com.lhm.qubaManage.util.RedisUtil;

/**  
 * 页面跳转控制器
 * @package: com.lhm.qubaManage.controller
 * @author: liu huangming
 * @date: 2019年12月27日 下午4:18:03 
 */
@Controller
@SuppressWarnings({"rawtypes","unchecked"})
public class PageController {
	
	@Value("${spring.rsa.privatekey}")
	private String privatekey; // 私钥
	@Value("${spring.rsa.publickey}")
	private String publickey; // 公钥
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	private Logger logger = LoggerFactory.getLogger(PageController.class);
	
	/**
	 * 跳转到登录
	 * @package: com.lhm.qubaManage.controller
	 * @param model
	 * @return
	 * @author: liu huangming
	 * @date: 2019年12月27日 下午4:19:30
	 */
	@RequestMapping(value = BaseControllerUrl.GO_LOGIN_HTM)
    public String goToLogin(Model model,HttpServletRequest request) {
		try {
			RedisUtil redisUtil = new RedisUtil();
			redisUtil.setRedisTemplate(redisTemplate);
			String ip = MainUtil.getIpAddr(request);
			String jsonString = (String)redisUtil.get(String.format(CommonConstant.REDIS_LOGIN_INFO, ip));
			ManageUser manageUser = JSONObject.parseObject(jsonString,ManageUser.class);
			if (!StringUtils.isBlank(jsonString)) {
				model.addAttribute(CommonConstant.LOGIN_NAME, manageUser.getLoginName());
				return "main";
			}
	    	model.addAttribute(CommonConstant.PUBLIC_KEY, this.publickey);
		} catch (Exception e) {
			logger.error("获取秘钥对错误:{}",e);
		}
    	return "login";
    }
	@RequestMapping(value = BaseControllerUrl.GO_MAIN_HTM)
	public String goToMain(Model model,HttpServletRequest request) {
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setRedisTemplate(redisTemplate);
		String ip = MainUtil.getIpAddr(request);
		String jsonString = (String)redisUtil.get(String.format(CommonConstant.REDIS_LOGIN_INFO, ip));
		ManageUser manageUser = JSONObject.parseObject(jsonString,ManageUser.class);
		model.addAttribute(CommonConstant.LOGIN_NAME, manageUser.getLoginName());
		return "main";
	}
}
