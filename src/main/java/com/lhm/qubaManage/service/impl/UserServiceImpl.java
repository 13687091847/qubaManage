package com.lhm.qubaManage.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.lhm.qubaManage.bean.Result;
import com.lhm.qubaManage.bean.ResultCodeEnum;
import com.lhm.qubaManage.common.CommonConstant;
import com.lhm.qubaManage.dao.ManageUserDAO;
import com.lhm.qubaManage.entity.ManageUser;
import com.lhm.qubaManage.entity.ManageUserExample;
import com.lhm.qubaManage.entity.ManageUserExample.Criteria;
import com.lhm.qubaManage.service.UserService;
import com.lhm.qubaManage.util.MainUtil;
import com.lhm.qubaManage.util.RSAUtils;
import com.lhm.qubaManage.util.RedisUtil;
import com.lhm.qubaManage.util.SendEmailUtil;
import com.lhm.qubaManage.util.WebSocketServer;
/**  
 * @package: com.lhm.qubaManage.service.impl
 * @author: liu huangming
 * @date: 2019年12月25日 下午7:33:29 
 */
@Service
@SuppressWarnings(value = {"rawtypes","unchecked"})
public class UserServiceImpl implements UserService{

	@Autowired
	private ManageUserDAO manageUserDao;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private SendEmailUtil sendEmailUtil;
	
	@Value("${spring.rsa.privatekey}")
	private String privatekey; // 私钥
	
	@Value("${spring.rsa.publickey}")
	private String publickey; // 公钥
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final long CODE_EXP_TIME = 300; // 验证码过期时间
	
	
	@Override
	public Result login(ManageUser manageUser,HttpServletRequest request) {
		// 解密
		try {
			RedisUtil redisUtil = new RedisUtil();
			redisUtil.setRedisTemplate(redisTemplate);
			// 获取IP
			// String ip = MainUtil.getIpAddr(request);
			if (StringUtils.isAllBlank(manageUser.getPassword(),manageUser.getEmail(),manageUser.getLoginName(),manageUser.getPhone())) {
				return new Result<>(false,ResultCodeEnum.PARAMS_IS_NULL);
			}
			String email = manageUser.getEmail();
			ManageUserExample example = new ManageUserExample();
			Criteria manageUserCri = example.createCriteria();
			if (!StringUtils.isBlank(email)) {
				// 邮箱登录
				manageUserCri.andEmailEqualTo(email);
				List<ManageUser> userList = manageUserDao.selectByExample(example);
				String code = (String)redisUtil.get(String.format(CommonConstant.REDIS_CODE, email,"login"));
				if (CollectionUtils.isEmpty(userList)) {
					// 用户不存在
					return new Result<ManageUser>(false, ResultCodeEnum.USER_NOT_EXIST);
				}
				if (code.equals(manageUser.getCode())) {
					userList.get(0).setLoginStatus(manageUser.getLoginStatus());
					return loginSuccess(userList.get(0),redisUtil);
				}else {
					return new Result<ManageUser>(false, ResultCodeEnum.CODE_ERROR);
				}
			}
			if (!StringUtils.isBlank(manageUser.getPhone()) && MainUtil.checkPhone(manageUser.getPhone())) {
				// 手机号登录
				manageUserCri.andPhoneEqualTo(manageUser.getPhone());
			}else {
				// 用户名登录
				manageUserCri.andLoginNameEqualTo(manageUser.getPhone());
			}
			// 对前端的加密密码进行解密
			String decryptPw = RSAUtils.decrypt(manageUser.getPassword(), this.privatekey);
			List<ManageUser> userList = manageUserDao.selectByExample(example);
			if (CollectionUtils.isEmpty(userList)) {
				// 用户不存在
				return new Result<ManageUser>(false, ResultCodeEnum.USER_NOT_EXIST);
			}
			// 获取数据库中的加密密码
			String pwd = userList.get(0).getPassword();
			// 用私钥解密
			pwd = RSAUtils.decrypt(pwd,this.privatekey);
			if (pwd.equals(decryptPw)) {
				userList.get(0).setLoginStatus(manageUser.getLoginStatus());
				return loginSuccess(userList.get(0),redisUtil);
			}else {
				return new Result<ManageUser>(false, ResultCodeEnum.USER_ACCOUNT_ERROR);
			}
		} catch (Exception e) {
			logger.error("登录失败:{}",e);
			return new Result<ManageUser>(false, ResultCodeEnum.INTERFACE_INNER_INVOKE_ERROR);
		}
	}

	/**
	 * 登录成功
	 * @package: com.lhm.qubaManage.service.impl
	 * @param user
	 * @type 0:邮箱登录 1:非邮箱登录
	 * @author: liu huangming
	 * @date: 2019年12月30日 上午9:50:46
	 */
	public Result loginSuccess(ManageUser user,RedisUtil redisUtil) {
		String 	currUser = (String)redisUtil.get(user.getPhone()+"login");
		// 判断当前用户是否已经登录
		WebSocketServer wss = new WebSocketServer();
		if (currUser != null) {  // 如果已经登录，则发送挤退信息
		    try {
		        wss.sendMessage(String.valueOf(ResultCodeEnum.USER_LOGIN_ALREADY.getCode()));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		// 保存用户信息和ip到redis,设置过期时间为7200s
		// 过期时间
		Long exeTime = null;
		if (user.getLoginStatus() == 1) {
			// 十天内免登陆
			exeTime = 864000L;
		}else {
			exeTime = 7200L;
		}
		String manageString = JSONObject.toJSONString(user);
		redisUtil.set(String.format(CommonConstant.REDIS_LOGIN_INFO, user.getPhone()), manageString, exeTime);
		return new Result<ManageUser>(true, ResultCodeEnum.SUCCESS);
	}
	@Override
	public Boolean checkUser(ManageUser manageUser, HttpSession session) {
		
		return null;
	}

	@Override
	public Result addUser(ManageUser manageUser) {
		try {
			if (manageUser.getPhone() == null|| manageUser.getEmail() == null) {
				return new Result<ManageUser>(false,ResultCodeEnum.PARAMS_IS_NULL);
			}
			if (checkExist(manageUser.getPhone(), manageUser.getEmail())) {
				return new Result<ManageUser>(false,ResultCodeEnum.PHONE_OR_EMAIL_EXIST);
			}
			if (manageUserDao.insertSelective(manageUser) > 0) {
				return new Result<ManageUser>(true);
			}else {
				return new Result<ManageUser>(false,ResultCodeEnum.USER_ADD_FAIL);
			}
		} catch (Exception e) {
			logger.error("用户添加失败:{}",e);
			return new Result<ManageUser>(false,ResultCodeEnum.USER_ADD_FAIL);
		}
	}

	/**
	 * 查询用户该手机号或者邮箱是否被使用
	 * @package: com.lhm.qubaManage.service.impl
	 * @param id
	 * @return
	 * @author: liu huangming
	 * @date: 2019年12月26日 下午6:39:23
	 */
	@Override
	public Boolean checkExist(String phone,String email) {
		Long countUser = manageUserDao.selectByPhoneOrEmail(phone, email);
		if (countUser != null && countUser > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Result sendCode(String email) {
		if (!checkExist("", email)) {
			return new Result<>(false,ResultCodeEnum.USER_NOT_EXIST);
		}
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setRedisTemplate(redisTemplate);
		String code = (String)redisUtil.get(String.format(CommonConstant.REDIS_CODE, email,"login"));
		if (StringUtils.isBlank(code)) {
			 // 已经过期,重新生成
		     int loginCode = (int)((Math.random()*9+1)*100000);
		     // 设置过期时间为5分钟
		     code = String.valueOf(loginCode);
		     redisUtil.set(String.format(CommonConstant.REDIS_CODE, email,"login"), code, CODE_EXP_TIME);
		}
		return sendEmailUtil.sendCodeEmail(email, String.valueOf(code));
	}
}
