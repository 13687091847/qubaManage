//package com.lhm.qubaManage.schedule;
//
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import com.lhm.qubaManage.common.CommonConstant;
//import com.lhm.qubaManage.controller.UserController;
//import com.lhm.qubaManage.dao.ManageUserDAO;
//import com.lhm.qubaManage.entity.ManageUser;
//import com.lhm.qubaManage.entity.ManageUserExample;
//import com.lhm.qubaManage.entity.ManageUserExample.Criteria;
//import com.lhm.qubaManage.util.RSAUtils;
//import com.lhm.qubaManage.util.RedisUtil;
//
///**
// * 更新redis中RSA秘钥对定时器
// * 
// * @package: com.lhm.qubaManage.thread
// * @author: liu huangming
// * @date: 2019年12月26日 下午4:37:39
// */
////要声明为bean，没有声明启动类启动无法实现定时效果
//@Component
//public class UpdateRedisSchedule {
//	@SuppressWarnings("rawtypes")
//	@Autowired
//	private RedisTemplate redisTemplate;
//	@Value("${spring.rsa.privatekey}")
//	private String privatekey; // 私钥
//	@Value("${spring.rsa.publickey}")
//	private String publickey; // 公钥
//	
//	private Logger logger = LoggerFactory.getLogger(UserController.class);
//	
//	@SuppressWarnings("unchecked")
//	@Scheduled(cron = "0 0 0 * * ?")//每天0点更新秘钥对以及更新用户的加密后的密码
//	public void work() {
//		RedisUtil redisUtil = new RedisUtil();
//		redisUtil.setRedisTemplate(redisTemplate);
//		try {
//			// 重新更新密钥对
//			Map<String, Object> newKeyMap = RSAUtils.getKeyPair();
//			// 获取所有的用户
//			ManageUserExample example = new ManageUserExample();
//			Criteria manCriteria = example.createCriteria();
//			List<ManageUser> userList = manageUserDAO.selectByExample(example);
//			if (CollectionUtils.isEmpty(userList)) {
//				logger.error("更新用户密码失败!");
//				return;
//			}
//			// 获取之前的老的密钥对
//			Map<String, Object> oldKeyMap = (Map<String, Object>)redisUtil.get(CommonConstant.KEY_MAP);
//			for (ManageUser manageUser : userList) {
//				// 先解密
//				String oldPw = RSAUtils.decrypt(manageUser.getPassword(), RSAUtils.getPrivateKey(oldKeyMap));
//				// 重新加密
//				String newPw = RSAUtils.encrypt(oldPw, RSAUtils.getPublicKey(newKeyMap));
//				// 更新密码密文
//				manageUser.setPassword(newPw);
//				manCriteria.andIdEqualTo(manageUser.getId());
//				manageUserDAO.updateByExampleSelective(manageUser, example);
//			}
//			// 更新redis中的密钥对
//			if (redisUtil.get(CommonConstant.PRIVATE_KEY) == null) {
//				redisUtil.set(CommonConstant.PRIVATE_KEY, this.privatekey);
//			}
//			if (redisUtil.get(CommonConstant.PUBLIC_KEY) == null) {
//				redisUtil.set(CommonConstant.PUBLIC_KEY, this.publickey);
//			}
//		} catch (Exception e) {
//			logger.error("更新秘钥对发生错误:{}",e);
//		}
//	}
//}
