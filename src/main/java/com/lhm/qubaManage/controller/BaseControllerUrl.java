package com.lhm.qubaManage.controller;

/**  
 * @package: com.lhm.qubaManage.controller
 * @author: liu huangming
 * @date: 2019年12月24日 下午4:46:58 
 */
public class BaseControllerUrl {
	public final static String SUFFIX = ".htm";
	public final static String USER_MANAGE_ROOT = "/user";
	// 页面跳转登录
	public final static String GO_LOGIN_HTM =  "/login" + SUFFIX;
	// 用户登录
	public final static String LOGIN_HTM = USER_MANAGE_ROOT + "/login" + SUFFIX;
	// 添加用户
	public final static String ADD_USER = USER_MANAGE_ROOT + "/addUser" + SUFFIX;
	// 获取验证码
	public final static String SEND_CODE = USER_MANAGE_ROOT + "/sendCode" + SUFFIX;
	// 跳转主页面
	public final static String GO_MAIN_HTM =  "/main" + SUFFIX;
}
