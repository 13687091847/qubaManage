package com.lhm.qubaManage.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lhm.qubaManage.bean.Result;
import com.lhm.qubaManage.entity.ManageUser;

/**
 * 用户管理
 * @author liuhuangming
 *
 */
@SuppressWarnings("rawtypes")
public interface UserService {

	/**
	 * 登录
	 * @package: com.lhm.qubaManage.service
	 * @param manageUser
	 * @return
	 * @author: liu huangming
	 * @date: 2019年12月25日 下午7:36:09
	 */
	Result login(ManageUser manageUser,HttpServletRequest request);
	/**
	 * 校验用户是否登录
	 * @package: com.lhm.qubaManage.service
	 * @param manageUser
	 * @param session
	 * @return
	 * @author: liu huangming
	 * @date: 2019年12月26日 下午1:46:38
	 */
	Boolean checkUser(ManageUser manageUser,HttpSession session);
	/**
	 * 添加用户
	 * @package: com.lhm.qubaManage.service
	 * @param manageUser
	 * @return
	 * @author: liu huangming
	 * @date: 2019年12月26日 下午6:30:32
	 */
	Result addUser(ManageUser manageUser);
	/**
	 * 检查手机号和邮箱是否已被使用
	 * @package: com.lhm.qubaManage.service
	 * @param phone
	 * @param email
	 * @return
	 * @author: liu huangming
	 * @date: 2019年12月26日 下午6:57:07
	 */
	Boolean checkExist(String phone,String email);
	/**
	 * 发送邮件
	 * @package: com.lhm.qubaManage.service
	 * @param email
	 * @return
	 * @author: liu huangming
	 * @date: 2019年12月26日 下午7:34:19
	 */
	Result sendCode(String email);
}
