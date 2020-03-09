package com.lhm.qubaManage.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhm.qubaManage.bean.Result;
import com.lhm.qubaManage.entity.ManageUser;
import com.lhm.qubaManage.service.UserService;

/**  
 * @package: com.lhm.qubaManage.controller
 * @author: liu huangming
 * @date: 2019年12月19日 下午5:48:19 
 */
@SuppressWarnings("rawtypes")
@Controller
public class UserController{

	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
    /**
     * 登录
     * @package: com.lhm.qubaManage.controller
     * @param manageUser
     * @return
     * @author: liu huangming
     * @date: 2019年12月25日 下午7:32:20
     */
	@RequestMapping(value = BaseControllerUrl.LOGIN_HTM)
    @ResponseBody
    public Result loginFun(ManageUser manageUser,HttpServletRequest request) {
    	return userService.login(manageUser,request);
    }
	
	/**
	 * 添加用户
	 * @package: com.lhm.qubaManage.controller
	 * @param manageUser
	 * @return
	 * @author: liu huangming
	 * @date: 2019年12月26日 下午6:29:47
	 */
	@RequestMapping(value = BaseControllerUrl.ADD_USER)
	@ResponseBody
	public Result addManageUser(ManageUser manageUser) {
		return userService.addUser(manageUser);
	}
	/**
	 * 获取验证码
	 * @package: com.lhm.qubaManage.controller
	 * @param email
	 * @return
	 * @author: liu huangming
	 * @date: 2019年12月26日 下午7:15:48
	 */
	@RequestMapping(value = BaseControllerUrl.SEND_CODE)
	@ResponseBody
	public Result getCode(String email) {
		return userService.sendCode(email);
	}
	
}
